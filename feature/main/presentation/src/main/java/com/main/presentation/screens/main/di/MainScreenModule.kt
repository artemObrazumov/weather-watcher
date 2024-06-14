package com.main.presentation.screens.main.di

import com.city.domain.usecase.GetCitiesUseCase
import com.main.presentation.screens.main.MainScreenViewModel
import com.weather.domain.usecase.GetWeatherUseCase
import dagger.Module
import dagger.Provides

@Module(
    includes = [UseCaseModule::class]
)
class MainScreenModule {

    @MainScreenScope
    @Provides
    fun provideMainScreenViewModel(
        getCitiesUseCase: GetCitiesUseCase,
        getWeatherUseCase: GetWeatherUseCase
    ): MainScreenViewModel =
        MainScreenViewModel(getCitiesUseCase, getWeatherUseCase)
}