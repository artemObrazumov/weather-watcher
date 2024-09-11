package com.weather.data.repository

import com.weather.data.local.repository.WeatherLocalRepository
import com.weather.data.remote.repository.WeatherRemoteRepository
import com.weather.data.utils.mappers.toDomain
import com.weather.domain.models.result.GetWeatherResult
import com.weather.domain.models.weather.Weather
import com.weather.domain.models.weather.WeatherLog
import com.weather.domain.repository.WeatherRepository
import java.time.LocalDateTime
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteRepository: WeatherRemoteRepository,
    private val weatherLocalRepository: WeatherLocalRepository
): WeatherRepository {

    override suspend fun getWeatherForCoordinates(
        latitude: Double,
        longitude: Double
    ): GetWeatherResult =
        weatherRemoteRepository.getWeather(latitude, longitude).toDomain()

    override suspend fun logWeather(
        weatherLog: WeatherLog
    ) {
        weatherLocalRepository.saveWeatherLog(weatherLog)
    }

    override suspend fun logUnsuccessfulWeatherLog(
        cityId: Int,
        errorMessage: String,
        time: LocalDateTime
    ) {
        weatherLocalRepository.saveUnsuccessfulWeatherLog(
            cityId = cityId,
            errorMessage = errorMessage,
            time = time
        )
    }
}