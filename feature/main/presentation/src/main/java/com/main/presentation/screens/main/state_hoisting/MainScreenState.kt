package com.main.presentation.screens.main.state_hoisting

import com.city.domain.models.city.CityPagingItem

sealed class MainScreenState {

    data class Content(
        val citiesSectionState: CitiesSectionState
    ): MainScreenState()
}

sealed class CitiesSectionState {

    data class Data(
        val cities: List<CityPagingItem>
    ): CitiesSectionState()

    data object Loading: CitiesSectionState()
}