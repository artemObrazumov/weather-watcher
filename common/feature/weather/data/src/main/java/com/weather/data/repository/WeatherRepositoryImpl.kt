package com.weather.data.repository

import com.weather.data.remote.repository.WeatherRemoteRepository
import com.weather.data.utils.mappers.toDomain
import com.weather.domain.models.result.GetWeatherResult
import com.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherRemoteRepository: WeatherRemoteRepository
): WeatherRepository {

    override suspend fun getWeatherForCoordinates(
        latitude: Double,
        longitude: Double
    ): GetWeatherResult =
        weatherRemoteRepository.getWeather(latitude, longitude).toDomain()
}