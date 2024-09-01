package com.main.presentation.screens.city_editor.state_hoisting

import com.common.ui.state_hoisting.TextFieldState

data class CityEditorScreenState(
    val cityNameState: TextFieldState,
    val cityXState: TextFieldState,
    val cityYState: TextFieldState,
    val isAddingNewCity: Boolean,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)