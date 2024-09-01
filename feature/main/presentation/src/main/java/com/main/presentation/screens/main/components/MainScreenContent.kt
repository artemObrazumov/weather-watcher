package com.main.presentation.screens.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.common.ui.components.city.CityItemRow
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
                    val cities = state.cities.collectAsState(initial = emptyList())

                    val pager = rememberPagerState(
                        pageCount = { cities.value.size }
                    )
                    LaunchedEffect(state.currentPage) {
                        pager.scrollToPage(state.currentPage)
                    }

                    CityItemRow(
                        currentPage = state.currentPage,
                        cityRowItems = cities.value,
                        onItemClick = {
                            onAction(MainScreenAction.OpenTab(it))
                        },
                        onNewItemClick = { onAction(MainScreenAction.AddNewCity) }
                    )
                }

                is MainScreenState.Loading -> {}
            }
        }
    }
}