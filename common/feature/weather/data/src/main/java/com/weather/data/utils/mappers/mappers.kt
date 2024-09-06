package com.weather.data.utils.mappers

import com.weather.data.remote.models.RemoteWeather
import com.weather.data.remote.models.result.RemoteWeatherResult
import com.weather.domain.models.result.GetWeatherResult
import com.weather.domain.models.weather.Weather

fun RemoteWeatherResult.toDomain() = when (this) {
    is RemoteWeatherResult.Success -> GetWeatherResult.Success(this.remoteWeather.toDomain())
    is RemoteWeatherResult.Failure -> GetWeatherResult.Failure("error")
}

fun RemoteWeather.toDomain() =
    Weather(
        temperature = this.main.temperature.toInt(),
        temperatureFeels = this.main.temperatureFeels.toInt(),
        temperatureMin = this.main.temperatureMin.toInt(),
        temperatureMax = this.main.temperatureMax.toInt(),
        pressure = this.main.pressure,
        humidity = this.main.humidity,
        pressureSeaLevel = this.main.pressureSeaLevel,
        pressureGroundLevel = this.main.pressureGroundLevel,
        windSpeed = this.wind.speed,
        windDirectionDegrees = this.wind.direction,
        windGust = this.wind.gust,
        clouds = this.clouds.all,
        visibility = this.visibility,
        city = this.city
    )