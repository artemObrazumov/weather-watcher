package com.city.domain.usecase

import com.city.domain.repository.CityRepository

class GetCitiesUseCase(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke() = cityRepository.getCities()
}