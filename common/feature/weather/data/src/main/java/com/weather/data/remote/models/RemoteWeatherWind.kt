package com.weather.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteWeatherWind(

    @SerialName("speed")
    val speed: Double,

    @SerialName("deg")
    val direction: Int,

    @SerialName("gust")
    val gust: Double
)
