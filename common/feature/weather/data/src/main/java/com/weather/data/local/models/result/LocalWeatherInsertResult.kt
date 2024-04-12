package com.weather.data.local.models.result

sealed class LocalWeatherInsertResult {

    data object Success: LocalWeatherInsertResult()
    data object Failure: LocalWeatherInsertResult()
}