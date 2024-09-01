package com.city.data.local.models.result

import com.database.city.room.entity.CityEntity
import kotlinx.coroutines.flow.Flow

sealed class CityEntityGetResult {

    data class Success(val itemFlow: Flow<CityEntity>): CityEntityGetResult()
    data object Failure: CityEntityGetResult()
}