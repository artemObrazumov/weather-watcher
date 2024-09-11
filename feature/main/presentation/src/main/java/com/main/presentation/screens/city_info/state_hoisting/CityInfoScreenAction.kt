package com.main.presentation.screens.city_info.state_hoisting

sealed class CityInfoScreenAction {
    data object EditCity: CityInfoScreenAction()
    data object DeleteCity: CityInfoScreenAction()
    data class OpenTab(
        val index: Int
    ) : CityInfoScreenAction()
}