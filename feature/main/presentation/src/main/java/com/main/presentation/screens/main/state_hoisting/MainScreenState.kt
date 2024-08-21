package com.main.presentation.screens.main.state_hoisting

import com.city.domain.models.City

data class MainScreenState (
    val citiesSectionState: CitiesSectionState
)

sealed class CitiesSectionState {

    data class Data(
        val cities: List<City>
    ): CitiesSectionState()

    data object Loading: CitiesSectionState()
}