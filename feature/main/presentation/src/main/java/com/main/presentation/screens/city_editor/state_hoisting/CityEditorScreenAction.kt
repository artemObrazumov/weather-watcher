package com.main.presentation.screens.city_editor.state_hoisting

sealed class CityEditorScreenAction {
    data class OnCityNameUpdated(val cityName: String): CityEditorScreenAction()
    data class OnCityLatitudeUpdated(val x: String): CityEditorScreenAction()
    data class OnCityLongitudeUpdated(val y: String): CityEditorScreenAction()
    data object OnMapOpened: CityEditorScreenAction()
    data object OnCitySaved: CityEditorScreenAction()
}