package com.city.domain.usecase

import com.city.domain.models.City
import com.city.domain.models.result.UpsertCityResult
import com.city.domain.repository.CityRepository
import javax.inject.Inject

class UpsertCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke(
        city: City
    ): UpsertCityResult = cityRepository.upsertCity(city)
}