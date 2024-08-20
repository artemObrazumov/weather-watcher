package com.city.domain.usecase

import com.city.domain.models.City
import com.city.domain.repository.CityRepository

class InsertCityUseCase(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke(
        city: City
    ) = cityRepository.insertCity(city)
}