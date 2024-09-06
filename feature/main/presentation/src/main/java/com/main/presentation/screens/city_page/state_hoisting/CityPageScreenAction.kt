package com.main.presentation.screens.city_page.state_hoisting

sealed class CityPageScreenAction {
    data object RetryLoadWeather: CityPageScreenAction()
}