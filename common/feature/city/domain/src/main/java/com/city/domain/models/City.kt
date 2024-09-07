package com.city.domain.models

data class City(
    val id: Int?,
    val name: String,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
