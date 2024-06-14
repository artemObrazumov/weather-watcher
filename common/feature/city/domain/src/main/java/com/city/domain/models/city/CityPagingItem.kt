package com.city.domain.models.city

sealed class CityPagingItem {

    data class City(
        val id: Long,
        val city: String
    ): CityPagingItem()

    data object Empty: CityPagingItem()
}
