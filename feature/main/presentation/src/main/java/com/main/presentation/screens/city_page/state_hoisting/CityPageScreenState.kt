package com.main.presentation.screens.city_page.state_hoisting

import com.weather.domain.models.weather.Weather

data class CityPageScreenState (
    val currentWeatherState: WeatherState,
    val cityErrorMessage: String?
)

sealed class WeatherState {
    data object Loading: WeatherState()
    data class Content(
        val weather: Weather
    ): WeatherState()
    data class Error(
        val errorMessage: String
    ): WeatherState()
}