package com.main.presentation.screens.city_info.state_hoisting

sealed class CityInfoScreenEffect {

    data class NavigateToCityEditScreen(
        val id: Int
    ): CityInfoScreenEffect()
    data object NavigateBack: CityInfoScreenEffect()
}