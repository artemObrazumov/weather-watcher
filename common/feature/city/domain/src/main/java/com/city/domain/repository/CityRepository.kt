package com.city.domain.repository

import com.city.domain.models.city.CityPagingItem
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.models.result.InsertCityResult
import com.city.domain.models.result.ModifyCityResult

interface CityRepository {

    suspend fun getCities(): GetCitiesResult

    suspend fun insertCity(city: CityPagingItem.City): InsertCityResult

    suspend fun modifyCity(city: CityPagingItem.City): ModifyCityResult
}