package com.main.presentation.di

import com.city.domain.models.city.CityPagingItem
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.models.result.InsertCityResult
import com.city.domain.models.result.ModifyCityResult
import com.city.domain.repository.CityRepository

class CityRepoTest: CityRepository {
    override suspend fun getCities(): GetCitiesResult {
        return GetCitiesResult.Success(listOf(
            CityPagingItem.City(id=1, city = "Нижний Новгород"),
            CityPagingItem.City(id=2, city = "Арзамас"),
            CityPagingItem.City(id=3, city = "Москва")
        ))
    }

    override suspend fun insertCity(city: CityPagingItem.City): InsertCityResult {
        return InsertCityResult.Failure
    }

    override suspend fun modifyCity(city: CityPagingItem.City): ModifyCityResult {
        return ModifyCityResult.Failure
    }

}