package com.common.ui.components.city

sealed class CityRowItem

data class City(
    val id: Long,
    val city: String
): CityRowItem()

data object Empty: CityRowItem()