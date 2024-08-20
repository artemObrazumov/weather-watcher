package com.main.presentation.screens.main.di

import com.city.domain.repository.CityRepository
import com.city.domain.usecase.GetCitiesUseCase
import com.main.presentation.screens.main.MainScreenViewModel
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCaseModule::class]
)
class MainScreenModule {

    @MainScreenScope
    @Provides
    fun provideMainScreenViewModel(
        cityRepository: CityRepository
    ): MainScreenViewModel =
        MainScreenViewModel(GetCitiesUseCase(cityRepository))
}