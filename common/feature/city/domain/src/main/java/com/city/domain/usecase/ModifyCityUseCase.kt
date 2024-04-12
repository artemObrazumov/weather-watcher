package com.city.domain.usecase

import com.city.domain.models.city.City
import com.city.domain.repository.CityRepository

class ModifyCityUseCase(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke(
        city: City
    ) = cityRepository.modifyCity(city)
}