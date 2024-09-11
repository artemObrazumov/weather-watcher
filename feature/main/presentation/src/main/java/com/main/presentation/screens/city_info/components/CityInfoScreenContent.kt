package com.main.presentation.screens.city_info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.city.domain.models.City
import com.common.ui.components.loading.LoadingScreen
import com.common.ui.components.tabs.WeatherWatcherTab
import com.common.ui.theme.WeatherWatcherTheme
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenAction
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenState
import com.main.presentation.screens.city_info.state_hoisting.CityInfoState
import com.main.presentation.screens.city_info.state_hoisting.CityMonitoringState
import com.main.presentation.screens.main.state_hoisting.MainScreenAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityInfoScreenContent(
    state: CityInfoScreenState,
    modifier: Modifier = Modifier,
    onAction: (CityInfoScreenAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when(state.cityInfoState) {
                            is CityInfoState.Data -> state.cityInfoState.city.name
                            else -> ""
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = WeatherWatcherTheme.paddings.medium)
            ) {
                Row(
                    modifier = Modifier
                        .padding(
                            top = WeatherWatcherTheme.paddings.medium,
                            bottom = WeatherWatcherTheme.paddings.large
                        )
                ) {
                    WeatherWatcherTab(
                        modifier = Modifier.weight(1f),
                        selected = state.activeTab == 0,
                        onClick = { onAction(CityInfoScreenAction.OpenTab(0)) }
                    ) {
                        Text(
                            text = "Город",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                    Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.medium))
                    WeatherWatcherTab(
                        modifier = Modifier.weight(1f),
                        selected = state.activeTab == 1,
                        onClick = { onAction(CityInfoScreenAction.OpenTab(1)) }
                    ) {
                        Text(
                            text = "Мониторинг",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
            val pagerState = rememberPagerState(
                initialPage = state.activeTab,
                pageCount = { 2 }
            )
            LaunchedEffect(state.activeTab) {
                pagerState.animateScrollToPage(state.activeTab)
            }
            LaunchedEffect(pagerState.currentPage) {
                onAction(CityInfoScreenAction.OpenTab(pagerState.currentPage))
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    WeatherWatcherTheme.paddings.medium
                ),
                pageSpacing = WeatherWatcherTheme.paddings.medium
            ) {
                when (it) {
                    0 -> CityInfoPage(
                        state = state.cityInfoState,
                        modifier = modifier,
                        onAction = onAction
                    )

                    1 -> CityMonitoringPage(
                        state = state.cityMonitoringState,
                        modifier = modifier,
                        onAction = onAction
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CityInfoScreenContentPreview() {
    WeatherWatcherTheme {
        val state = CityInfoScreenState(
            cityInfoState = CityInfoState.Data(
                city = City(
                    id = 0,
                    name = "test",
                    latitude = 0.0,
                    longitude = 0.0,
                )
            ),
            cityMonitoringState = CityMonitoringState.Loading,
            isProcessingOperation = false,
            activeTab = 0
        )
        CityInfoScreenContent(
            state = state,
            onAction = {}
        )
    }
}