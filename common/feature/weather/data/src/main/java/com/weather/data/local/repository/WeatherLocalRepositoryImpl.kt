package com.weather.data.local.repository

import com.database.city.room.entity.WeatherLogEntity
import com.weather.data.local.models.filters.TimeFilter
import com.weather.data.local.models.result.WeatherLogGetResult
import com.weather.data.local.models.result.WeatherLogUpsertResult
import com.weather.data.utils.mappers.toEntity
import com.weather.domain.models.weather.WeatherLog
import java.time.LocalDateTime
import javax.inject.Inject

class WeatherLocalRepositoryImpl @Inject constructor(
    private val weatherLogDao: com.database.city.room.dao.WeatherLogDao
): WeatherLocalRepository {

    override suspend fun saveWeatherLog(
        weatherLog: WeatherLog
    ): WeatherLogUpsertResult {
        return try {
            WeatherLogUpsertResult.Success(
                weatherLogDao.upsertWeatherLog(weatherLog.toEntity()).toInt()
            )
        } catch (e: Exception) {
            WeatherLogUpsertResult.Failure("database error")
        }
    }

    override suspend fun saveUnsuccessfulWeatherLog(
        cityId: Int,
        time: LocalDateTime,
        errorMessage: String
    ): WeatherLogUpsertResult {
        return try {
            WeatherLogUpsertResult.Success(
                weatherLogDao.upsertWeatherLog(
                    WeatherLogEntity(
                        id = null,
                        cityId = cityId,
                        successful = false,
                        errorMessage = errorMessage,
                        time = time
                    )
                ).toInt()
            )
        } catch (e: Exception) {
            WeatherLogUpsertResult.Failure("database error")
        }
    }

    override suspend fun loadFromWeatherLog(timeFilter: TimeFilter): WeatherLogGetResult {
        TODO("Not yet implemented")
    }
}