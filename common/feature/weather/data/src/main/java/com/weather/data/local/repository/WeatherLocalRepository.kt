package com.weather.data.local.repository

import com.weather.data.local.models.filters.TimeFilter
import com.weather.data.local.models.result.LocalWeatherGetResult
import com.weather.data.local.models.result.LocalWeatherInsertResult
import com.weather.domain.models.weather.Weather
import java.time.LocalDateTime

interface WeatherLocalRepository {

    suspend fun saveToWeatherLog(
        weather: Weather,
        time: LocalDateTime
    ): LocalWeatherInsertResult

    suspend fun loadFromWeatherLog(
        timeFilter: TimeFilter
    ): LocalWeatherGetResult
}