package com.main.presentation.screens.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.city.domain.models.city.CityPagingItem
import com.common.ui.components.city.CityItemRow
import com.main.presentation.screens.main.state_hoisting.CitiesSectionState
import com.main.presentation.screens.main.state_hoisting.MainScreenAction

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenContentState(
    citiesSectionState: CitiesSectionState,
    modifier: Modifier = Modifier,
    onAction: (MainScreenAction) -> Unit = {}
) {

    val section = citiesSectionState as CitiesSectionState.Data
    val pager = rememberPagerState(
        pageCount = { section.cities.size }
    )
    Column {
        CityItemRow(
            pagerState = pager,
            cityRowItems = section.cities)
    }
}