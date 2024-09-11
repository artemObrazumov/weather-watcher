package com.main.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.city.domain.models.City
import com.city.domain.models.result.CitiesGetResult
import com.city.domain.usecase.GetCitiesUseCase
import com.common.ui.state_hoisting.StatefulViewModel
import com.main.presentation.screens.main.state_hoisting.CitiesState
import com.main.presentation.screens.main.state_hoisting.MainScreenAction
import com.main.presentation.screens.main.state_hoisting.MainScreenEffect
import com.main.presentation.screens.main.state_hoisting.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : StatefulViewModel<MainScreenState, MainScreenEffect, MainScreenAction>() {

    private var citiesState: CitiesState = CitiesState.Loading
    private var currentPage = 0

    val state = _state.receiveAsFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            MainScreenState(
                citiesState = citiesState,
                currentPage = currentPage
            )
        )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCitiesUseCase.invoke()
            when (result) {
                is CitiesGetResult.Failure -> {}
                is CitiesGetResult.Success -> {
                    result.citiesFlow.collect {
                        citiesState = CitiesState.Data(
                            cities = it
                        )
                        updateStateWithNewStates()
                    }
                }
            }
        }
    }

    override fun onAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.AddNewCity -> addNewCity()
            is MainScreenAction.OpenTab -> {
                if (state.value.citiesState is CitiesState.Data) {
                    viewModelScope.launch {
                        currentPage = action.index
                        updateStateWithNewStates()
                    }
                }
            }

            is MainScreenAction.OpenDetails -> {
                viewModelScope.launch(Dispatchers.IO) {
                    updateEffect(MainScreenEffect.NavigateToCityDetails(action.id))
                }
            }
        }
    }

    private suspend fun updateStateWithNewStates() {
        updateState(
            MainScreenState(
                citiesState = citiesState,
                currentPage = currentPage
            )
        )
    }

    private fun addNewCity() {
        viewModelScope.launch {
            updateEffect(MainScreenEffect.NavigateToAddNewCity)
        }
    }
}