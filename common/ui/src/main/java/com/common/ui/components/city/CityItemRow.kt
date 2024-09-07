package com.common.ui.components.city

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
    modifier: Modifier = Modifier,
    currentPage: Int,
    cityRowItems: List<City>,
    onItemClick: (index: Int) -> Unit,
    onNewItemClick: () -> Unit,
    onDetailsClick: (id: Int) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
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
                city = it.name,
                selected = index == currentPage,
                onClick = { onItemClick(index) },
                onDetailsClick = { onDetailsClick(it.id ?: -1) }
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
                        City(id = 1, name = "Moscow"),
                        City(id = 2, name = "Arzamas")
                    ),
                    onItemClick = {},
                    onNewItemClick = {},
                    onDetailsClick = {}
                )
            }
        }
    }
}