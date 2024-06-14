package com.main.presentation.di

import dagger.Module
import dagger.Subcomponent

@MainFeatureScope
@Subcomponent(modules = [MainFeatureModule::class])
interface MainFeatureComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): MainFeatureComponent
    }
}