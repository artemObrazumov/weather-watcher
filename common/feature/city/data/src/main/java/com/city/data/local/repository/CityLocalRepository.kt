package com.city.data.local.repository

import com.city.domain.models.City
import com.city.data.local.models.result.CitiesEntityGetResult
import com.city.data.local.models.result.CitiesUpsertResult
import com.city.data.local.models.result.CitiesModifyResult
import com.city.data.local.models.result.CityEntityGetResult

interface CityLocalRepository {

    suspend fun loadCities(): CitiesEntityGetResult
    suspend fun upsertCity(city: City): CitiesUpsertResult
    suspend fun modifyCity(city: City): CitiesModifyResult
    suspend fun getCityById(cityId: Int): CityEntityGetResult
    suspend fun deleteCity(cityId: Int)
}