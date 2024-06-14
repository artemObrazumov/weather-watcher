package com.city.data.repository

import com.city.data.local.repository.CityLocalRoomRepositoryImpl
import com.city.data.local.utils.toCity
import com.city.data.local.utils.toDomain
import com.city.domain.models.city.CityPagingItem
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.models.result.InsertCityResult
import com.city.domain.models.result.ModifyCityResult
import com.city.domain.repository.CityRepository

class CityRepositoryImpl(
    private val cityLocalRoomRepositoryImpl: CityLocalRoomRepositoryImpl
): CityRepository {
    override suspend fun getCities(): GetCitiesResult =
        cityLocalRoomRepositoryImpl.loadCities().toDomain()

    override suspend fun insertCity(city: CityPagingItem.City): InsertCityResult =
        cityLocalRoomRepositoryImpl.insertCity(city.toCity()).toDomain()

    override suspend fun modifyCity(city: CityPagingItem.City): ModifyCityResult =
        cityLocalRoomRepositoryImpl.modifyCity(city.toCity()).toDomain()
}