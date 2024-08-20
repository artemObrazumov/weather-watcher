package com.city.data.local.utils

import com.city.domain.models.City
import com.city.data.local.models.result.CitiesGetResult
import com.city.data.local.models.result.CitiesInsertResult
import com.city.data.local.models.result.CitiesModifyResult
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.models.result.InsertCityResult
import com.city.domain.models.result.ModifyCityResult
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

fun CitiesGetResult.toDomain(): GetCitiesResult = when (this) {
    is CitiesGetResult.Success -> GetCitiesResult.Success(this.items.map { it.toCity() })
    is CitiesGetResult.Failure -> GetCitiesResult.Failure
}

fun CitiesInsertResult.toDomain(): InsertCityResult = when (this) {
    is CitiesInsertResult.Success -> InsertCityResult.Success(this.id)
    is CitiesInsertResult.Failure -> InsertCityResult.Failure
}

fun CitiesModifyResult.toDomain(): ModifyCityResult = when (this) {
    is CitiesModifyResult.Success -> ModifyCityResult.Success
    is CitiesModifyResult.Failure -> ModifyCityResult.Failure
}