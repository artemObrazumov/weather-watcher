package com.main.presentation.di

import com.main.presentation.navigation.MainFeatureNavigationApi
import com.weatherwatcher.di.NavigationScope
import com.weatherwatcher.navigation.NavigationItemApi
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class MainFeatureNavigationModule {

    @NavigationScope
    @Provides
    @IntoSet
    fun provideMainFeatureNavigationApi(): NavigationItemApi =
        MainFeatureNavigationApi()
}