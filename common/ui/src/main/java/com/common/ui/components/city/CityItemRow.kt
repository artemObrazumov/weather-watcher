package com.common.ui.components.city

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.common.ui.theme.WeatherWatcherTheme

@Composable
fun CityItemRow(
    cityRowItems: List<CityRowItem>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cityRowItems) {
            when (it) {
                is City -> {
                    CityItem(
                        modifier = Modifier
                            .clickable {  },
                        city = it.city
                    )
                }
                is Empty -> {
                    CityItem(
                        isNewItemPlaceholder = true
                    )
                }
            }
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
                CityItemRow(cityRowItems = listOf(
                    City(id = 1L, city = "Moscow"),
                    City(id = 2L, city = "Arzamas"),
                    Empty
                ))
            }
        }
    }
}