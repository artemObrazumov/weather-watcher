package com.main.presentation.screens.city_editor.state_hoisting

sealed class CityEditorScreenAction {
    data class OnCityNameUpdated(val cityName: String): CityEditorScreenAction()
    data class OnCityXUpdated(val x: String): CityEditorScreenAction()
    data class OnCityYUpdated(val y: String): CityEditorScreenAction()
    data object OnMapOpened: CityEditorScreenAction()
    data object OnCitySaved: CityEditorScreenAction()
}