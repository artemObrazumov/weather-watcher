package com.main.presentation.screens.city_editor.state_hoisting

sealed class CityEditorScreenEffect {
    data object OpenMap: CityEditorScreenEffect()
    data object NavigateBack: CityEditorScreenEffect()
}