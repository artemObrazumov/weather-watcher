package com.city.data.local.models.result

import com.database.city.room.entity.CityEntity

sealed class CitiesGetResult {

    data class Success(val items: List<CityEntity>): CitiesGetResult()
    data object Failure: CitiesGetResult()
}