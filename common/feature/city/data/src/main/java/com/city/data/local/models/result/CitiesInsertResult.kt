package com.city.data.local.models.result

sealed class CitiesInsertResult {

    data class Success(val id: Long): CitiesInsertResult()
    data object Failure: CitiesInsertResult()
}