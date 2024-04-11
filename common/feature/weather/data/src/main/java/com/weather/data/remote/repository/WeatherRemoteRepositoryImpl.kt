package com.weather.data.remote.repository

import com.weather.data.local.location.LocationManager
import com.weather.data.remote.models.result.RemoteWeatherResult
import com.weather.data.remote.network.WeatherNetworkService

class WeatherRemoteRepositoryImpl(
    private val weatherNetworkService: WeatherNetworkService,
    private val locationManager: LocationManager
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