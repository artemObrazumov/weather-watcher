package com.common.ui.components.weather

data class WeatherWidgetUI(
    val temperature: String,
    val temperatureFeels: String,
    val temperatureRange: String,
    val pressure: String,
    val humidity: String,
    val pressureSeaLevel: String,
    val pressureGroundLevel: String,
    val windSpeed: String,
    val windDirection: String,
    val windDirectionDegrees: Float,
    val windGust: String,
    val clouds: String,
    val visibility: String,
    val city: String,
)
