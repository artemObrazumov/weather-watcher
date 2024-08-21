package com.main.presentation.screens.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.common.ui.components.city.CityItemRow
import com.main.presentation.screens.main.state_hoisting.CitiesSectionState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CitiesRowComponent(
    state: CitiesSectionState,
    modifier: Modifier = Modifier
) {
    when (state) {
        CitiesSectionState.Loading -> {
            CircularProgressIndicator()
        }
        is CitiesSectionState.Data -> {
            val pager = rememberPagerState(
                pageCount = { state.cities.size }
            )

            CityItemRow(
                pagerState = pager,
                cityRowItems = state.cities
            )
        }
    }
}