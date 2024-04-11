package com.weather.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteWeatherMain(

    @SerialName("temp")
    val temperature: Double,

    @SerialName("feels_like")
    val temperatureFeels: Double,

    @SerialName("temp_min")
    val temperatureMin: Double,

    @SerialName("temp_max")
    val temperatureMax: Double,

    @SerialName("pressure")
    val pressure: Int,

    @SerialName("humidity")
    val humidity: Int,

    @SerialName("sea_level")
    val pressureSeaLevel: Int,

    @SerialName("grnd_level")
    val pressureGroundLevel: Int,
)