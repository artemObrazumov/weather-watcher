package com.main.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.city.domain.models.City
import com.city.domain.models.result.CitiesGetResult
import com.city.domain.usecase.GetCitiesUseCase
import com.common.ui.state_hoisting.StatefulViewModel
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
    getCitiesUseCase: GetCitiesUseCase
) : StatefulViewModel<MainScreenState, MainScreenEffect, MainScreenAction>() {

    val state = _state.receiveAsFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            MainScreenState.Loading
        )

    private var citiesFlow: Flow<List<City>> = emptyFlow()
    private var currentPage = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCitiesUseCase.invoke()
            if (result is CitiesGetResult.Failure) return@launch

            citiesFlow = (result as CitiesGetResult.Success).citiesFlow
            updateState(
                MainScreenState.Data(
                    cities = citiesFlow,
                    currentPage = currentPage
                )
            )
        }
    }

    override fun onAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.AddNewCity -> addNewCity()
            is MainScreenAction.OpenTab -> {
                if (state.value is MainScreenState.Data) {
                    viewModelScope.launch {
                        currentPage = action.index
                        updateState(
                            MainScreenState.Data(
                                cities = citiesFlow,
                                currentPage = currentPage
                            )
                        )
                    }
                }
            }
        }
    }

    private fun addNewCity() {
        viewModelScope.launch {
            updateEffect(MainScreenEffect.NavigateToAddNewCity)
        }
    }
}