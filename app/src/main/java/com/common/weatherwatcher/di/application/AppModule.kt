package com.common.weatherwatcher.di.application

import com.common.weatherwatcher.di.navigation.NavigationComponent
import com.database.city.room.di.CityDatabaseModule
import com.main.presentation.di.MainFeatureComponent
import dagger.Module

@Module(
    subcomponents = [NavigationComponent::class, MainFeatureComponent::class],
    includes = [CityDatabaseModule::class]
)
class AppModule