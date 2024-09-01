package com.city.data.local.models.result

sealed class CitiesUpsertResult {

    data class Success(val id: Int): CitiesUpsertResult()
    data object Failure: CitiesUpsertResult()
}