package com.city.data.repository

import com.city.data.local.repository.CityLocalRepository
import com.city.data.local.utils.toDomain
import com.city.domain.models.City
import com.city.domain.models.result.CitiesGetResult
import com.city.domain.models.result.CityGetResult
import com.city.domain.models.result.UpsertCityResult
import com.city.domain.models.result.ModifyCityResult
import com.city.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityLocalRepository: CityLocalRepository
): CityRepository {
    override suspend fun getCities(): CitiesGetResult =
        cityLocalRepository.loadCities().toDomain()

    override suspend fun upsertCity(city: City): UpsertCityResult =
        cityLocalRepository.upsertCity(city).toDomain()

    override suspend fun modifyCity(city: City): ModifyCityResult =
        cityLocalRepository.modifyCity(city).toDomain()

    override suspend fun getCity(cityId: Int): CityGetResult =
        cityLocalRepository.getCityById(cityId).toDomain()

    override suspend fun deleteCity(cityId: Int) {
        cityLocalRepository.deleteCity(cityId)
    }
}