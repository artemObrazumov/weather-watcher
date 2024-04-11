package com.weather.data.remote.network

import com.weather.data.remote.models.RemoteWeather
import com.weatherwatcher.keys.Keys
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherNetworkService {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = Keys.OpenWeather.apiKey,
        @Query("lang") language: String = "ru",
        @Query("units") units: String = "metric"
    ): Response<RemoteWeather>
}