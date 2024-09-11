package com.weather.domain.repository

import com.weather.domain.models.result.GetWeatherResult
import com.weather.domain.models.weather.Weather
import com.weather.domain.models.weather.WeatherLog
import java.time.LocalDateTime

interface WeatherRepository {

    suspend fun getWeatherForCoordinates(
        latitude: Double,
        longitude: Double
    ): GetWeatherResult

    suspend fun logWeather(
        weatherLog: WeatherLog,
    )

    suspend fun logUnsuccessfulWeatherLog(
        cityId: Int,
        errorMessage: String,
        time: LocalDateTime
    )
}