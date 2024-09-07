package com.city.domain.repository

import com.city.domain.models.City
import com.city.domain.models.result.CitiesGetResult
import com.city.domain.models.result.CityGetResult
import com.city.domain.models.result.UpsertCityResult
import com.city.domain.models.result.ModifyCityResult

interface CityRepository {

    suspend fun getCities(): CitiesGetResult
    suspend fun upsertCity(city: City): UpsertCityResult
    suspend fun modifyCity(city: City): ModifyCityResult
    suspend fun getCity(cityId: Int): CityGetResult
    suspend fun deleteCity(cityId: Int)
}