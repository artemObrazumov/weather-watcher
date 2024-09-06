package com.weather.data.remote.repository

import com.weather.data.remote.models.result.RemoteWeatherResult
import com.weather.data.remote.network.WeatherNetworkService
import javax.inject.Inject

class WeatherRemoteRepositoryImpl @Inject constructor(
    private val weatherNetworkService: WeatherNetworkService
): WeatherRemoteRepository {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): RemoteWeatherResult {
        return try {
            val result = weatherNetworkService.getWeather(latitude, longitude)
            if (result.isSuccessful) RemoteWeatherResult.Success(result.body()!!)
            else RemoteWeatherResult.Failure
        } catch (e: Exception) {
            e.printStackTrace()
            RemoteWeatherResult.Failure
        }
    }
}