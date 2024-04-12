package com.weather.data.local.db.dao

import androidx.room.Insert
import androidx.room.Query
import com.weather.data.local.db.entity.WeatherLogEntity
import com.city.data.local.models.filters.TimeFilter
import com.weather.domain.models.weather.Weather
import java.time.LocalDateTime

interface WeatherLogDao {

    @Insert
    suspend fun insertWeatherLog(
        weatherLog: WeatherLogEntity
    ): Long

    @Query("SELECT * FROM ${WeatherLogEntity.TABLE} WHERE ${WeatherLogEntity.CITY} = :city AND ${WeatherLogEntity.TIME} BETWEEN :fromTime AND :toTime")
    suspend fun loadWeatherLog(
        fromTime: LocalDateTime,
        toTime: LocalDateTime,
        city: String
    ): List<WeatherLogEntity>
}