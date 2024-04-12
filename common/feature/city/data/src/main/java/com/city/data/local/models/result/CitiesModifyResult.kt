package com.city.data.local.models.result

sealed class CitiesModifyResult {

    data class Success(val id: Long): CitiesModifyResult()
    data object Failure: CitiesModifyResult()
}