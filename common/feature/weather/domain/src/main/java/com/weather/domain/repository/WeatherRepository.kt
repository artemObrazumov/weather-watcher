package com.weather.domain.repository

import com.weather.domain.models.result.GetWeatherResult

interface WeatherRepository {

    suspend fun getWeatherForCoordinates(
        latitude: Double,
        longitude: Double
    ): GetWeatherResult
}