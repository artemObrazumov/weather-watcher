package com.common.ui.components.city

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.city.domain.models.City
import com.common.ui.theme.WeatherWatcherTheme

@Composable
fun CityItemRow(
    currentPage: Int,
    cityRowItems: List<City>,
    onItemClick: (index: Int) -> Unit,
    onNewItemClick: () -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            start = WeatherWatcherTheme.paddings.medium,
            end = WeatherWatcherTheme.paddings.medium,
            bottom = 16.dp
        )
    ) {
        itemsIndexed(
            items = cityRowItems,
            key = { _, item -> item.id ?: -1 }
        ) { index, it ->
            CityItem(
                city = it.city,
                selected = index == currentPage,
                onClick = { onItemClick(index) }
            )
        }

        item {
            CityItem(
                isNewItemPlaceholder = true,
                onClick = onNewItemClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CityItemRowPreview() {
    WeatherWatcherTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                CityItemRow(
                    currentPage = 0,
                    cityRowItems = listOf(
                        City(id = 1, city = "Moscow"),
                        City(id = 2, city = "Arzamas")
                    ),
                    onItemClick = {},
                    onNewItemClick = {}
                )
            }
        }
    }
}