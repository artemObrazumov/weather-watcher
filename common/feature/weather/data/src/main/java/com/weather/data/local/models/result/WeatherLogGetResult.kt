package com.weather.data.local.models.result

import com.database.city.room.entity.WeatherLogEntity
import kotlinx.coroutines.flow.Flow

sealed class WeatherLogGetResult {

    data class Success(
        val items: Flow<List<WeatherLogEntity>>
    ): WeatherLogGetResult()
    data class Failure(
        val errorMessage: String
    ): WeatherLogGetResult()
}