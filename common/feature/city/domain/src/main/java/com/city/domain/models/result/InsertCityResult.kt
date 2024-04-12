package com.city.domain.models.result

sealed class InsertCityResult {

    data class Success(val id: Long): InsertCityResult()
    data object Failure: InsertCityResult()
}