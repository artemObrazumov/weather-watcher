package com.main.presentation.screens.main.components

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
import androidx.compose.ui.Modifier
import com.common.ui.components.city.CityItemRow
import com.common.ui.components.loading.LoadingScreen
import com.common.ui.theme.WeatherWatcherTheme
import com.main.presentation.screens.main.state_hoisting.CitiesState
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
            when (state.citiesState) {
                is CitiesState.Data -> {

                    val pagerState = rememberPagerState(
                        pageCount = { state.citiesState.cities.size }
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
                        cityRowItems = state.citiesState.cities,
                        onItemClick = { onAction(MainScreenAction.OpenTab(it)) },
                        onNewItemClick = { onAction(MainScreenAction.AddNewCity) },
                        onDetailsClick = { onAction(MainScreenAction.OpenDetails(it)) }
                    )

                    Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.medium))

                    if (state.citiesState.cities.isNotEmpty()) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(
                                horizontal = WeatherWatcherTheme.paddings.medium
                            ),
                            pageSpacing = WeatherWatcherTheme.paddings.medium
                        ) { index ->
                            CityPagerPage(
                                cityId = state.citiesState.cities[index].id ?: -1
                            )
                        }
                    }
                }
                is CitiesState.Loading -> {
                    LoadingScreen()
                }
                is CitiesState.Error -> {

                }
            }
        }
    }
}