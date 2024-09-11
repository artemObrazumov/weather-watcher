package com.weather.data.local.models.result

sealed class WeatherLogUpsertResult {

    data class Success(
        val id: Int
    ): WeatherLogUpsertResult()
    data class Failure(
        val errorMessage: String
    ): WeatherLogUpsertResult()
}