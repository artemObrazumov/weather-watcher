package com.weather.data.remote.repository

import com.weather.data.remote.models.result.RemoteWeatherResult

interface WeatherRemoteRepository {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): RemoteWeatherResult
}