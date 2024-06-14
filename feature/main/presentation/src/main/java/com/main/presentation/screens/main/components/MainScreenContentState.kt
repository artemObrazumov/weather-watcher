package com.main.presentation.screens.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.main.presentation.screens.main.state_hoisting.CitiesSectionState
import com.main.presentation.screens.main.state_hoisting.MainScreenAction

@Composable
fun MainScreenContentState(
    citiesSectionState: CitiesSectionState,
    modifier: Modifier = Modifier,
    onAction: (MainScreenAction) -> Unit = {}
) {

}