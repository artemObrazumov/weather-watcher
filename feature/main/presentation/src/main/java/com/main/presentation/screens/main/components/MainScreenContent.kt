package com.main.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.common.ui.components.city.CityItemRow
import com.common.ui.components.loading.LoadingScreen
import com.common.ui.theme.WeatherWatcherTheme
import com.main.presentation.screens.city_page.state_hoisting.WeatherState
import com.main.presentation.screens.main.state_hoisting.MainScreenAction
import com.main.presentation.screens.main.state_hoisting.MainScreenState

@Composable
fun MainScreenContent(
    state: MainScreenState,
    modifier: Modifier = Modifier,
    onAction: (MainScreenAction) -> Unit = {}
) {

    Scaffold { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            when (state) {
                is MainScreenState.Data -> {
                    val cities by state.cities.collectAsState(initial = emptyList())

                    val pagerState = rememberPagerState(
                        pageCount = { cities.size }
                    )
                    LaunchedEffect(state.currentPage) {
                        pagerState.animateScrollToPage(state.currentPage)
                    }
                    LaunchedEffect(pagerState.currentPage) {
                        onAction(MainScreenAction.OpenTab(pagerState.currentPage))
                    }

                    CityItemRow(
                        modifier = Modifier.padding(top = WeatherWatcherTheme.paddings.medium),
                        currentPage = state.currentPage,
                        cityRowItems = cities,
                        onItemClick = { onAction(MainScreenAction.OpenTab(it)) },
                        onNewItemClick = { onAction(MainScreenAction.AddNewCity) },
                        onDetailsClick = { onAction(MainScreenAction.OpenDetails(it)) }
                    )

                    Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.medium))

                    if (cities.isNotEmpty()) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(
                                horizontal = WeatherWatcherTheme.paddings.medium
                            )
                        ) { index ->
                            CityPagerPage(
                                cityId = cities[index].id ?: -1
                            )
                        }
                    }
                }

                is MainScreenState.Loading -> {
                    LoadingScreen()
                }
            }
        }
    }
}