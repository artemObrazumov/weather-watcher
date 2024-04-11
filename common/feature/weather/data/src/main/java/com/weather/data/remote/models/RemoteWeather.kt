package com.weather.data.remote.models

import kotlinx.serialization.*

@Serializable
data class RemoteWeather(

    @SerialName("main")
    val main: RemoteWeatherMain,

    @SerialName("wind")
    val wind: RemoteWeatherWind,

    @SerialName("clouds")
    val clouds: RemoteWeatherClouds,

    @SerialName("visibility")
    val visibility: Int,

    @SerialName("name")
    val city: String
)
