package com.city.domain.usecase

import com.city.domain.models.city.CityPagingItem
import com.city.domain.repository.CityRepository

class InsertCityUseCase(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke(
        city: CityPagingItem.City
    ) = cityRepository.insertCity(city)
}