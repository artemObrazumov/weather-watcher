package com.city.domain.models.result

sealed class ModifyCityResult {

    data object Success: ModifyCityResult()
    data object Failure: ModifyCityResult()
}