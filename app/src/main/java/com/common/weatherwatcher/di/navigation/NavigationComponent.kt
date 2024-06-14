package com.common.weatherwatcher.di.navigation

import com.main.presentation.di.MainFeatureNavigationModule
import com.weatherwatcher.di.NavigationScope
import com.weatherwatcher.navigation.NavigationItemApi
import dagger.Component
import dagger.Subcomponent

@NavigationScope
@Subcomponent(
    modules = [MainFeatureNavigationModule::class]
)
interface NavigationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NavigationComponent
    }

    val navigationItemApis: Set<@JvmSuppressWildcards NavigationItemApi>
}