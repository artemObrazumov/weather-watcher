package com.city.data.local.models.result

import com.city.data.local.models.City
import com.database.city.room.entity.CityEntity

sealed class CitiesGetResult {

    data class Success(val items: List<City>): CitiesGetResult()
    data object Failure: CitiesGetResult()
}