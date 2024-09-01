package com.city.data.local.models.result

import com.database.city.room.entity.CityEntity
import kotlinx.coroutines.flow.Flow

sealed class CitiesEntityGetResult {

    data class Success(val itemsFlow: Flow<List<CityEntity>>): CitiesEntityGetResult()
    data object Failure: CitiesEntityGetResult()
}