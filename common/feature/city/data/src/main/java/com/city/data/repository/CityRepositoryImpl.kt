package com.city.data.repository

import com.city.data.local.repository.CityLocalRepository
import com.city.data.local.utils.toDomain
import com.city.domain.models.City
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.models.result.InsertCityResult
import com.city.domain.models.result.ModifyCityResult
import com.city.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityLocalRepository: CityLocalRepository
): CityRepository {
    override suspend fun getCities(): GetCitiesResult =
        cityLocalRepository.loadCities().toDomain()

    override suspend fun insertCity(city: City): InsertCityResult =
        cityLocalRepository.insertCity(city).toDomain()

    override suspend fun modifyCity(city: City): ModifyCityResult =
        cityLocalRepository.modifyCity(city).toDomain()
}