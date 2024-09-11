package com.common.ui.components.city

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.common.ui.components.tabs.WeatherWatcherTab
import com.common.ui.theme.WeatherWatcherTheme

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    city: String = String(),
    selected: Boolean = false,
    isNewItemPlaceholder: Boolean = false,
    onClick: () -> Unit = {},
    onDetailsClick: () -> Unit = {}
) {
    WeatherWatcherTab(
        modifier = modifier,
        selected = selected,
        onClick = onClick
    ) {
        if (isNewItemPlaceholder) {
            EmptyCityItemContent()
        } else {
            CityItemContent(
                city = city,
                selected = selected,
                onDetailsClick = onDetailsClick
            )
        }
    }
}

@Composable
fun CityItemContent(
    city: String,
    selected: Boolean = false,
    onDetailsClick: () -> Unit
) {
    Row {
        Image(
            Icons.Default.Place,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                if (selected) Color.White else Color.Black
            )
        )
        Text(
            text = city,
            style = MaterialTheme.typography.labelMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .padding(
                    horizontal = WeatherWatcherTheme.paddings.medium,
                    vertical = WeatherWatcherTheme.paddings.default
                ),
            color = if (selected) Color.White else Color.Black
        )
        val rotation by animateFloatAsState(
            targetValue = if (!selected) 120f else 0f,
            label = "$city rotation",
            animationSpec = tween(durationMillis = 400)
        )
        AnimatedVisibility(
            visible = selected,
            enter = expandHorizontally() + scaleIn() + slideInHorizontally(),
            exit = shrinkHorizontally() + scaleOut() + slideOutHorizontally()
        ) {
            Image(
                Icons.Default.Settings,
                modifier = Modifier
                    .rotate(rotation)
                    .clickable { onDetailsClick() },
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun EmptyCityItemContent() {
    Row {
        Image(
            Icons.Default.Add,
            contentDescription = null
        )
        Text(
            text = "Добавить",
            style = MaterialTheme.typography.labelMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .padding(
                    horizontal = WeatherWatcherTheme.paddings.medium,
                    vertical = WeatherWatcherTheme.paddings.default
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityItemPreview() {
    WeatherWatcherTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Row {
                    CityItem(city = "Moscow")
                    CityItem(city = "Moscow", selected = true)
                    CityItem(isNewItemPlaceholder = true)
                }
            }
        }
    }
}