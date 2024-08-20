package com.main.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.usecase.GetCitiesUseCase
import com.common.ui.state_hoisting.StatefulViewModel
import com.main.presentation.screens.main.state_hoisting.CitiesSectionState
import com.main.presentation.screens.main.state_hoisting.MainScreenAction
import com.main.presentation.screens.main.state_hoisting.MainScreenEffect
import com.main.presentation.screens.main.state_hoisting.MainScreenState
import com.weather.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainScreenViewModel(
    getCitiesUseCase: GetCitiesUseCase,
    //getWeatherUseCase: GetWeatherUseCase
): StatefulViewModel<MainScreenState, MainScreenEffect, MainScreenAction>() {

    val state = _state.receiveAsFlow()
        .stateIn(viewModelScope,
            SharingStarted.Eagerly,
            MainScreenState.Content(citiesSectionState = CitiesSectionState.Loading)
        )

    init {
        viewModelScope.launch {
            val res = getCitiesUseCase.invoke()
            if (res is GetCitiesResult.Failure) return@launch
            updateState(MainScreenState.Content(
                citiesSectionState = CitiesSectionState.Data( (res as GetCitiesResult.Success).cities )
            ))
        }
    }

    override fun onAction(action: MainScreenAction) {
        when(action) {
            else -> {}
        }
    }
}