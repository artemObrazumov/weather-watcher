package com.common.ui.components.city

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.city.domain.models.city.CityPagingItem
import com.common.ui.theme.WeatherWatcherTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityItemRow(
    pagerState: PagerState,
    cityRowItems: List<CityPagingItem>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(cityRowItems) { index, it ->
            when (it) {
                is CityPagingItem.City -> {
                    CityItem(
                        modifier = Modifier
                            .clickable {  },
                        city = it.city,
                        selected = index == pagerState.currentPage
                    )
                }
                is CityPagingItem.Empty -> {
                    CityItem(
                        isNewItemPlaceholder = true
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun CityItemRowPreview() {
    WeatherWatcherTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val pager = rememberPagerState(
                pageCount = { 3 }
            )
            Column {
                CityItemRow(
                    pagerState = pager,
                    cityRowItems = listOf(
                        CityPagingItem.City(id = 1L, city = "Moscow"),
                        CityPagingItem.City(id = 2L, city = "Arzamas"),
                        CityPagingItem.Empty
                ))
            }
        }
    }
}