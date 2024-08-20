package com.city.data.repository

import com.city.data.local.repository.CityLocalRepository
import com.city.data.local.utils.toDomain
import com.city.domain.models.City
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.models.result.InsertCityResult
import com.city.domain.models.result.ModifyCityResult
import com.city.domain.repository.CityRepository

class CityRepositoryImpl(
    private val cityLocalRoomRepositoryImpl: CityLocalRepository
): CityRepository {
    override suspend fun getCities(): GetCitiesResult =
        cityLocalRoomRepositoryImpl.loadCities().toDomain()

    override suspend fun insertCity(city: City): InsertCityResult =
        cityLocalRoomRepositoryImpl.insertCity(city).toDomain()

    override suspend fun modifyCity(city: City): ModifyCityResult =
        cityLocalRoomRepositoryImpl.modifyCity(city).toDomain()
}