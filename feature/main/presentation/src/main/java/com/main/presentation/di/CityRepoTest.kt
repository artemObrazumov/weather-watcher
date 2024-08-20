package com.main.presentation.di

import com.city.domain.models.City
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.models.result.InsertCityResult
import com.city.domain.models.result.ModifyCityResult
import com.city.domain.repository.CityRepository

class CityRepoTest: CityRepository {
    override suspend fun getCities(): GetCitiesResult {
        return GetCitiesResult.Success(listOf(
            City(id=1, city = "Нижний Новгород"),
            City(id=2, city = "Арзамас"),
            City(id=3, city = "Москва")
        ))
    }

    override suspend fun insertCity(city: City): InsertCityResult {
        return InsertCityResult.Failure
    }

    override suspend fun modifyCity(city: City): ModifyCityResult {
        return ModifyCityResult.Failure
    }

}