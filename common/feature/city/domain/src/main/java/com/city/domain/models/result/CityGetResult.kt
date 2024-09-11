package com.city.domain.models.result

import com.city.domain.models.City
import kotlinx.coroutines.flow.Flow

sealed class CityGetResult {

    data class Success(val cityFlow: Flow<City>): CityGetResult()
    data class Failure(val errorMessage: String): CityGetResult()
}