package com.city.domain.models.result

sealed class UpsertCityResult {

    data class Success(val id: Int): UpsertCityResult()
    data object Failure: UpsertCityResult()
}