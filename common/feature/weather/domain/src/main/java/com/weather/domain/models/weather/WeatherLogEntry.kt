package com.weather.domain.models.weather

import java.time.LocalDateTime

sealed class WeatherLogEntry {
    data class UnsuccessfulLog(
        val cityId: Int,
        val errorMessage: String,
        val time: LocalDateTime,
    ): WeatherLogEntry()
    data class SuccessfulLog(
        val weatherLog: WeatherLog
    ): WeatherLogEntry()
}