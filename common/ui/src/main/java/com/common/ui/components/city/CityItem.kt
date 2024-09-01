package com.common.ui.components.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemobrazumov.ui.R
import com.common.ui.theme.Purple40
import com.common.ui.theme.Purple80
import com.common.ui.theme.WeatherWatcherTheme

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    city: String = String(),
    selected: Boolean = false,
    isNewItemPlaceholder: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                clip = false,
                shape = RoundedCornerShape(8.dp),
                ambientColor = Color.Black,
                spotColor = Color.Gray
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = WeatherWatcherTheme.purpleRipple,
                onClick = onClick
            )
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) Purple80 else Color.White)
            .padding(8.dp)
    ) {
        if (isNewItemPlaceholder) {
            EmptyCityItemContent()
        } else {
            CityItemContent(
                city = city,
                selected = selected
            )
        }
    }
}

@Composable
fun CityItemContent(
    city: String,
    selected: Boolean = false,
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