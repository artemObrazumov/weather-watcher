package com.city.data.local.utils

import com.city.domain.models.City
import com.city.data.local.models.result.CitiesEntityGetResult
import com.city.data.local.models.result.CitiesUpsertResult
import com.city.data.local.models.result.CitiesModifyResult
import com.city.data.local.models.result.CityEntityGetResult
import com.city.domain.models.result.CitiesGetResult
import com.city.domain.models.result.CityGetResult
import com.city.domain.models.result.UpsertCityResult
import com.city.domain.models.result.ModifyCityResult
import com.database.city.room.entity.CityEntity
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

fun CityEntity.toCity(): City =
    City(
        id = this.id,
        name = this.city,
        latitude = this.latitude,
        longitude = this.longitude
    )

fun City.toCityEntity(): CityEntity =
    CityEntity(
        id = this.id,
        city = this.name,
        latitude = this.latitude,
        longitude = this.longitude
    )

fun CitiesEntityGetResult.toDomain(): CitiesGetResult = when (this) {
    is CitiesEntityGetResult.Success -> CitiesGetResult.Success(
        this.itemsFlow.map {
            it.map { cityEntity -> cityEntity.toCity()  }
        }
    )
    is CitiesEntityGetResult.Failure -> CitiesGetResult.Failure
}

fun CitiesUpsertResult.toDomain(): UpsertCityResult = when (this) {
    is CitiesUpsertResult.Success -> UpsertCityResult.Success(this.id)
    is CitiesUpsertResult.Failure -> UpsertCityResult.Failure
}

fun CitiesModifyResult.toDomain(): ModifyCityResult = when (this) {
    is CitiesModifyResult.Success -> ModifyCityResult.Success
    is CitiesModifyResult.Failure -> ModifyCityResult.Failure
}

fun CityEntityGetResult.toDomain(): CityGetResult = when (this) {
    is CityEntityGetResult.Success -> CityGetResult.Success(
        this.itemFlow.mapNotNull { cityEntity: CityEntity? ->
            cityEntity?.toCity()
        }
    )
    is CityEntityGetResult.Failure -> CityGetResult.Failure
}