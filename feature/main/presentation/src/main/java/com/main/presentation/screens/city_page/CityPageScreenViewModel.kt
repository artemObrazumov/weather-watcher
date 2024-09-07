package com.main.presentation.screens.city_page

import androidx.lifecycle.viewModelScope
import com.city.domain.models.City
import com.city.domain.models.result.CityGetResult
import com.city.domain.usecase.GetCityUseCase
import com.common.ui.state_hoisting.StatefulViewModel
import com.main.presentation.screens.city_page.state_hoisting.CityPageScreenAction
import com.main.presentation.screens.city_page.state_hoisting.CityPageScreenEffect
import com.main.presentation.screens.city_page.state_hoisting.CityPageScreenState
import com.main.presentation.screens.city_page.state_hoisting.WeatherState
import com.weather.domain.models.result.GetWeatherResult
import com.weather.domain.usecase.GetWeatherUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel(assistedFactory = CityPageScreenViewModel.Factory::class)
class CityPageScreenViewModel @AssistedInject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getCityUseCase: GetCityUseCase,
    @Assisted private val cityId: Int
): StatefulViewModel<CityPageScreenState, CityPageScreenEffect, CityPageScreenAction>() {

    private var currentWeatherState: WeatherState = WeatherState.Loading
    private var cityInfo: City? = null
    private var cityError: String? = null

    val state = _state.receiveAsFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            CityPageScreenState(
                currentWeatherState = currentWeatherState,
                cityErrorMessage = cityError
            )
        )

    init {
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            if (cityInfo == null) {
                loadCityInfo()
            }

            currentWeatherState = WeatherState.Loading
            updateStateWithNewStates()

            val result = getWeatherUseCase.invoke(
                latitude = cityInfo?.latitude ?: 0.0,
                longitude = cityInfo?.longitude ?: 0.0
            )
            when (result) {
                is GetWeatherResult.Success -> {
                    currentWeatherState = WeatherState.Content(result.weather)
                }
                is GetWeatherResult.Failure -> {
                    currentWeatherState = WeatherState.Error(errorMessage = result.errorMessage)
                }
            }
            updateStateWithNewStates()
        }
    }

    private suspend fun loadCityInfo() {
        cityError = null
        updateStateWithNewStates()

        val result = getCityUseCase.invoke(cityId)
        when (result) {
            is CityGetResult.Failure -> {
                cityError = "error city"
            }
            is CityGetResult.Success -> {
                runBlocking {
                    cityInfo = result.cityFlow.firstOrNull()
                }
            }
        }
    }

    override fun onAction(action: CityPageScreenAction) {
        when (action) {
            is CityPageScreenAction.RetryLoadWeather -> {
                loadWeather()
            }
        }
    }

    private suspend fun updateStateWithNewStates() {
        updateState(
            CityPageScreenState(
                currentWeatherState = currentWeatherState,
                cityErrorMessage = cityError
            )
        )
    }

    @AssistedFactory
    interface Factory {
        fun create(cityId: Int): CityPageScreenViewModel
    }
}