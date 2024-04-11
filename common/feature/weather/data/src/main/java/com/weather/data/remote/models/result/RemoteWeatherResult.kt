package com.weather.data.remote.models.result

import com.weather.data.remote.models.RemoteWeather

sealed class RemoteWeatherResult {

    data class Success(val remoteWeather: RemoteWeather):
        RemoteWeatherResult()

    data object Failure: RemoteWeatherResult()
}
