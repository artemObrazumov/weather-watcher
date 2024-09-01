package com.city.domain.usecase

import com.city.domain.models.City
import com.city.domain.repository.CityRepository
import javax.inject.Inject

class ModifyCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke(
        city: City
    ) = cityRepository.modifyCity(city)
}