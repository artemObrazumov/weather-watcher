package com.city.domain.models.result

import com.city.domain.models.City
import kotlinx.coroutines.flow.Flow

sealed class CitiesGetResult {

    data class Success(val citiesFlow: Flow<List<City>>): CitiesGetResult()
    data object Failure: CitiesGetResult()
}