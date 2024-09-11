package com.weather.data.utils.mappers

import com.database.city.room.entity.WeatherLogEntity
import com.weather.data.remote.models.RemoteWeather
import com.weather.data.remote.models.result.RemoteWeatherResult
import com.weather.domain.models.result.GetWeatherResult
import com.weather.domain.models.weather.Weather
import com.weather.domain.models.weather.WeatherLog
import java.time.LocalDateTime

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

fun Weather.toWeatherLog(
    id: Int? = null,
    time: LocalDateTime,
    cityId: Int,
): WeatherLog =
    WeatherLog(
        id = id,
        cityId = cityId,
        temperature = this.temperature,
        temperatureFeels = this.temperatureFeels,
        temperatureMin = this.temperatureMin,
        temperatureMax = this.temperatureMax,
        pressure = this.pressure,
        humidity = this.humidity,
        pressureSeaLevel = this.pressureSeaLevel,
        pressureGroundLevel = this.pressureGroundLevel,
        windSpeed = this.windSpeed,
        windDirectionDegrees = this.windDirectionDegrees,
        windGust = this.windGust,
        clouds = this.clouds,
        visibility = this.visibility,
        city = this.city,
        time = time
    )

fun WeatherLog.toEntity(): WeatherLogEntity =
    WeatherLogEntity(
        id = this.id,
        successful = true,
        time = this.time,
        errorMessage = null,
        temperature = this.temperature,
        temperatureFeels = this.temperatureFeels,
        temperatureMin = this.temperatureMin,
        temperatureMax = this.temperatureMax,
        pressure = this.pressure,
        humidity = this.humidity,
        pressureSeaLevel = this.pressureSeaLevel,
        pressureGroundLevel = this.pressureGroundLevel,
        windSpeed = this.windSpeed,
        windDirectionDegrees = this.windDirectionDegrees,
        windGust = this.windGust,
        clouds = this.clouds,
        visibility = this.visibility,
        city = this.city,
        cityId = this.cityId
    )