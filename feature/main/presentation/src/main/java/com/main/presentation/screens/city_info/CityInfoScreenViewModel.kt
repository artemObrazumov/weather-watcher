package com.main.presentation.screens.city_info

import androidx.lifecycle.viewModelScope
import com.city.domain.models.result.CityGetResult
import com.city.domain.usecase.DeleteCityUseCase
import com.city.domain.usecase.GetCityUseCase
import com.common.ui.state_hoisting.StatefulViewModel
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenAction
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenEffect
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenState
import com.main.presentation.screens.city_info.state_hoisting.CityInfoState
import com.main.presentation.screens.city_info.state_hoisting.CityMonitoringState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CityInfoScreenViewModel.Factory::class)
class CityInfoScreenViewModel @AssistedInject constructor(
    private val getCityUseCase: GetCityUseCase,
    private val deleteCityUseCase: DeleteCityUseCase,
    @Assisted private val cityId: Int
): StatefulViewModel<CityInfoScreenState, CityInfoScreenEffect, CityInfoScreenAction>() {

    private var cityInfoState: CityInfoState = CityInfoState.Loading
    private var cityMonitoringState: CityMonitoringState = CityMonitoringState.Loading
    private var isProcessingOperation = false
    private var activeTab = 0

    val state = _state.receiveAsFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            CityInfoScreenState(
                cityInfoState = cityInfoState,
                cityMonitoringState = cityMonitoringState,
                isProcessingOperation = isProcessingOperation,
                activeTab = activeTab
            )
        )

    init {
        loadCityData()
    }

    private fun loadCityData() {
        viewModelScope.launch(Dispatchers.IO) {
            cityInfoState = CityInfoState.Loading
            updateStateWithNewStates()

            val result = getCityUseCase.invoke(cityId)
            when(result) {
                is CityGetResult.Failure -> {
                    cityInfoState = CityInfoState.Error("error")
                    updateStateWithNewStates()
                }
                is CityGetResult.Success -> {
                    result.cityFlow.collect { city ->
                        cityInfoState = CityInfoState.Data(city)
                        updateStateWithNewStates()
                    }
                }
            }
        }
    }

    private fun deleteCity() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCityUseCase.invoke(cityId)
            updateEffect(CityInfoScreenEffect.NavigateBack)
        }
    }

    private suspend fun updateStateWithNewStates() {
        updateState(
            CityInfoScreenState(
                cityInfoState = cityInfoState,
                cityMonitoringState = cityMonitoringState,
                isProcessingOperation = isProcessingOperation,
                activeTab = activeTab
            )
        )
    }

    override fun onAction(action: CityInfoScreenAction) {
        when(action) {
            is CityInfoScreenAction.EditCity -> {
                viewModelScope.launch {
                    updateEffect(CityInfoScreenEffect.NavigateToCityEditScreen(cityId))
                }
            }
            is CityInfoScreenAction.DeleteCity -> { deleteCity() }
            is CityInfoScreenAction.OpenTab -> {
                viewModelScope.launch {
                    activeTab = action.index
                    updateStateWithNewStates()
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(cityId: Int): CityInfoScreenViewModel
    }
}