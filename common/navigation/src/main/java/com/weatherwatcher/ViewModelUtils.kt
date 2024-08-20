package com.weatherwatcher

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Suppress("UNCHECKED_CAST")
@Composable
inline fun <reified T: ViewModel> daggerViewModel(
    key: String? = null,
    crossinline viewModelInstance: () -> T
): T = viewModel(
    modelClass = T::class,
    key = key,
    factory = object: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModelInstance() as T
        }
    }
)