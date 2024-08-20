package com.main.presentation.di

import com.main.presentation.screens.main.di.MainScreenComponent
import dagger.Module
import dagger.Subcomponent

@MainFeatureScope
@Subcomponent(modules = [MainFeatureModule::class])
interface MainFeatureComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): MainFeatureComponent
    }

    val mainScreenComponentFactory: MainScreenComponent.Factory
}