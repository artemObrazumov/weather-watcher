package com.weather.data.local.repository

import com.weather.data.local.models.filters.TimeFilter
import com.weather.data.local.models.result.WeatherLogGetResult
import com.weather.data.local.models.result.WeatherLogUpsertResult
import com.weather.domain.models.weather.WeatherLog
import java.time.LocalDateTime

interface WeatherLocalRepository {

    suspend fun saveWeatherLog(
        weatherLog: WeatherLog
    ): WeatherLogUpsertResult

    suspend fun saveUnsuccessfulWeatherLog(
        cityId: Int,
        time: LocalDateTime,
        errorMessage: String
    ): WeatherLogUpsertResult

    suspend fun loadFromWeatherLog(
        timeFilter: TimeFilter
    ): WeatherLogGetResult
}