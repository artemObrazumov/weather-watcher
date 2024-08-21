package com.main.presentation.screens.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.main.presentation.screens.main.state_hoisting.MainScreenAction
import com.main.presentation.screens.main.state_hoisting.MainScreenState

@Composable
fun MainScreenContent(
    state: MainScreenState,
    modifier: Modifier = Modifier,
    onAction: (MainScreenAction) -> Unit = {}
) {

    Column(
        modifier = modifier
    ) {
        CitiesRowComponent(
            state = state.citiesSectionState
        )
    }
}