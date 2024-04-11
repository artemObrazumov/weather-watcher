package com.weather.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteWeatherClouds(

    @SerialName("all")
    val all: Int
)
