package com.city.data.local.repository

import com.city.domain.models.City
import com.city.data.local.models.result.CitiesGetResult
import com.city.data.local.models.result.CitiesInsertResult
import com.city.data.local.models.result.CitiesModifyResult

interface CityLocalRepository {

    suspend fun loadCities(): CitiesGetResult
    suspend fun insertCity(city: City): CitiesInsertResult
    suspend fun modifyCity(city: City): CitiesModifyResult
}