package com.weather.domain.models.weather

data class Weather(
    val temperature: Int,
    val temperatureFeels: Int,
    val temperatureMin: Int,
    val temperatureMax: Int,
    val pressure: Int,
    val humidity: Int,
    val pressureSeaLevel: Int,
    val pressureGroundLevel: Int,
    val windSpeed: Double,
    val windDirectionDegrees: Int,
    val windGust: Double,
    val clouds: Int,
    val visibility: Int,
    val city: String,
)
