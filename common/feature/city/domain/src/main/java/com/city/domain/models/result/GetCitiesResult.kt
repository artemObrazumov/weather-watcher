package com.city.domain.models.result

import com.city.domain.models.City

sealed class GetCitiesResult {

    data class Success(val cities: List<City>): GetCitiesResult()
    data object Failure: GetCitiesResult()
}