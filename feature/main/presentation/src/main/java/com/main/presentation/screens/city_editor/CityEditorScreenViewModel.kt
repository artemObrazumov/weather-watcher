package com.main.presentation.screens.city_editor

import androidx.lifecycle.viewModelScope
import com.city.domain.models.City
import com.city.domain.models.result.CityGetResult
import com.city.domain.models.result.UpsertCityResult
import com.city.domain.usecase.GetCityUseCase
import com.city.domain.usecase.UpsertCityUseCase
import com.common.ui.state_hoisting.StatefulViewModel
import com.common.ui.state_hoisting.TextFieldState
import com.main.presentation.screens.city_editor.state_hoisting.CityEditorScreenAction
import com.main.presentation.screens.city_editor.state_hoisting.CityEditorScreenEffect
import com.main.presentation.screens.city_editor.state_hoisting.CityEditorScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CityEditorScreenViewModel.Factory::class)
class CityEditorScreenViewModel @AssistedInject constructor(
    private val upsertCityUseCase: UpsertCityUseCase,
    private val getCityUseCase: GetCityUseCase,
    @Assisted val cityId: Int
) : StatefulViewModel<CityEditorScreenState, CityEditorScreenEffect, CityEditorScreenAction>() {

    private val isAddingNewCity = cityId == -1
    private var cityNameState = TextFieldState()
    private var cityXState = TextFieldState()
    private var cityYState = TextFieldState()
    private var isLoading = false
    private var errorMessage: String? = null

    val state = _state.receiveAsFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            CityEditorScreenState(
                cityNameState = cityNameState,
                cityXState = cityXState,
                cityYState = cityYState,
                isAddingNewCity = isAddingNewCity,
                isLoading = isLoading,
                errorMessage = errorMessage
            )
        )

    init {
        loadCity()
    }

    private fun loadCity() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isAddingNewCity) {
                val result = getCityUseCase.invoke(cityId)
                when (result) {
                    is CityGetResult.Failure -> {
                        errorMessage = "Ошибка при загрузке города"
                    }

                    is CityGetResult.Success -> {
                        result.cityFlow.collect { city ->
                            cityNameState = cityNameState.copy(text = city.city)
                            cityXState = cityXState.copy(text = city.x.toString())
                            cityYState = cityYState.copy(text = city.y.toString())
                            updateStateWithNewStates()
                        }
                        isLoading = false
                    }
                }
            } else {
                isLoading = false
            }
            delay(3000L)
            updateStateWithNewStates()
        }
    }

    override fun onAction(action: CityEditorScreenAction) {
        viewModelScope.launch {
            when (action) {
                is CityEditorScreenAction.OnCityNameUpdated -> {
                    cityNameState = cityNameState.copy(text = action.cityName)
                }

                is CityEditorScreenAction.OnCityXUpdated -> {
                    cityXState = cityXState.copy(text = action.x)
                }

                is CityEditorScreenAction.OnCityYUpdated -> {
                    cityYState = cityYState.copy(text = action.y)
                }

                is CityEditorScreenAction.OnMapOpened -> {
                    updateEffect(CityEditorScreenEffect.OpenMap)
                }

                is CityEditorScreenAction.OnCitySaved -> {
                    validateAndSaveCity()
                }
            }
            updateStateWithNewStates()
        }
    }

    private fun validateAndSaveCity() {
        if (isFormValid()) {
            viewModelScope.launch(Dispatchers.IO) {
                val city = City(
                    id = if (cityId == -1) null else cityId,
                    city = cityNameState.text,
                    x = cityXState.text.toDouble(),
                    y = cityYState.text.toDouble(),
                )
                isLoading = true
                updateStateWithNewStates()
                val upsertCityResult = upsertCityUseCase.invoke(city)
                when (upsertCityResult) {
                    is UpsertCityResult.Success -> {
                        updateEffect(CityEditorScreenEffect.NavigateBack)
                    }

                    is UpsertCityResult.Failure -> {
                        errorMessage = "Ошибка при сохранении города"
                        updateStateWithNewStates()
                    }
                }
            }
        }
    }

    private fun isFormValid(): Boolean {
        var result = true
        if (cityNameState.text.length !in 2..20) {
            result = false
            cityNameState = cityNameState.copy(errorMessage = "Некорректная длина названия города")
        }

        try {
            cityXState.text.toDouble()
        } catch (_: Exception) {
            result = false
            cityXState = cityXState.copy(errorMessage = "Неправильный формат координат")
        }

        try {
            cityYState.text.toDouble()
        } catch (_: Exception) {
            result = false
            cityYState = cityYState.copy(errorMessage = "Неправильный формат координат")
        }
        return result
    }

    private suspend fun updateStateWithNewStates() {
        updateState(
            CityEditorScreenState(
                cityNameState = cityNameState,
                cityXState = cityXState,
                cityYState = cityYState,
                isAddingNewCity = isAddingNewCity,
                isLoading = isLoading,
                errorMessage = errorMessage
            )
        )
    }

    @AssistedFactory
    interface Factory {
        fun create(cityId: Int): CityEditorScreenViewModel
    }
}