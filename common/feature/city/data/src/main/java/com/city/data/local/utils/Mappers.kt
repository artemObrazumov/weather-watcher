package com.city.data.local.utils

import com.city.data.local.models.City
import com.database.city.room.entity.CityEntity

fun CityEntity.toCity(): City =
    City(
        id = this.id,
        city = this.city
    )

fun City.toCityEntity(): CityEntity =
    CityEntity(
        id = this.id,
        city = this.city
    )