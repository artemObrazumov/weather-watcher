package com.common.ui.components.weather.utils

import com.common.ui.components.weather.WeatherWidgetUI
import com.weather.domain.models.weather.Weather
import kotlin.math.abs

fun Weather.toWeatherUI(): WeatherWidgetUI =
    WeatherWidgetUI(
        temperature = this.temperature.asTemperatureString(),
        temperatureFeels = "Ощущается как: ${this.temperatureFeels.asTemperatureString()}",
        temperatureRange = "От ${this.temperatureMin.asTemperatureString()} до ${this.temperatureMax.asTemperatureString()}",
        pressure = this.pressure.asPressureString(),
        humidity = this.humidity.asHumidityString(),
        pressureSeaLevel = this.pressureSeaLevel.asSeaPressureString(),
        pressureGroundLevel = this.pressureGroundLevel.asGroundPressureString(),
        windSpeed = this.windSpeed.asWindSpeedString(),
        windDirection = this.windDirectionDegrees.asWindDirectionString(),
        windDirectionDegrees = -this.windDirectionDegrees.toFloat() - 90,
        windGust = this.windGust.asWindGustString(),
        clouds = this.clouds.asCloudsString(),
        visibility = this.visibility.asVisibilityString(),
        city = this.city
    )

internal fun Int.asTemperatureString(): String {
    val sign = if (this >= 0) "+" else "-"
    return "$sign ${abs(this)}°C"
}

internal fun Int.asPressureString(): String {
    return "$this кПа"
}

internal fun Int.asSeaPressureString(): String {
    return "В море: $this кПа"
}

internal fun Int.asGroundPressureString(): String {
    return "На земле: $this кПа"
}

internal fun Int.asHumidityString(): String {
    return "$this %"
}

internal fun Double.asWindSpeedString(): String {
    return "$this м/с"
}

internal fun Int.asWindDirectionString(): String {
    return "$this°"
}

internal fun Double.asWindGustString(): String {
    return "Порывы до: $this м/с"
}

internal fun Int.asCloudsString(): String {
    return "$this %"
}

internal fun Int.asVisibilityString(): String {
    return "$this м"
}