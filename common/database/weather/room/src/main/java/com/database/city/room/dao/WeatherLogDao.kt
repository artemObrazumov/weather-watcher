package com.database.city.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.database.city.room.entity.WeatherLogEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface WeatherLogDao {

    @Upsert
    suspend fun upsertWeatherLog(
        weatherLog: WeatherLogEntity
    ): Long

    @Query("SELECT * FROM ${WeatherLogEntity.TABLE} WHERE ${WeatherLogEntity.CITY} = :city AND ${WeatherLogEntity.TIME} BETWEEN :fromTime AND :toTime")
    fun getWeatherLog(
        fromTime: LocalDateTime,
        toTime: LocalDateTime,
        city: String
    ): Flow<List<WeatherLogEntity>>
}