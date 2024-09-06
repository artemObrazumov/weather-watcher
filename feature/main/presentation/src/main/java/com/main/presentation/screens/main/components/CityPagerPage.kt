package com.main.presentation.screens.main.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.main.presentation.screens.city_page.CityPageScreen
import com.main.presentation.screens.city_page.CityPageScreenViewModel

@Composable
fun CityPagerPage(
    cityId: Int,
    modifier: Modifier = Modifier
) {
    val viewModel: CityPageScreenViewModel =
        hiltViewModel<CityPageScreenViewModel, CityPageScreenViewModel.Factory>(
            key = cityId.toString(),
            creationCallback = { factory -> factory.create(cityId) }
        )
    CityPageScreen(
        viewModel = viewModel
    )
}