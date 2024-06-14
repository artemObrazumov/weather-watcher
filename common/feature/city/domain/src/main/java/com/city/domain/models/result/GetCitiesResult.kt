package com.city.domain.models.result

import com.city.domain.models.city.CityPagingItem

sealed class GetCitiesResult {

    data class Success(val cities: List<CityPagingItem>): GetCitiesResult()
    data object Failure: GetCitiesResult()
}