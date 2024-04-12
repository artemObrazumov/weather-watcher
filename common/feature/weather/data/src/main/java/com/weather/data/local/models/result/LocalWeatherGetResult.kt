package com.weather.data.local.models.result

import com.weather.data.local.db.entity.WeatherLogEntity

sealed class LocalWeatherGetResult {

    data class Success(val items: List<WeatherLogEntity>): LocalWeatherGetResult()
    data object Failure: LocalWeatherGetResult()
}