package com.common.ui.components.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemobrazumov.ui.R
import com.common.ui.theme.WeatherWatcherTheme

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    city: String = String(),
    isNewItemPlaceholder: Boolean = false
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = Color.Black,
                spotColor = Color.Gray
            )
            .clip(RoundedCornerShape(8.dp))
    ) {
        if (isNewItemPlaceholder) {
            EmptyCityItemContent()
        } else {
            CityItemContent(city = city)
        }
    }
}

@Composable
fun CityItemContent(
    city: String
) {
    Row {
        Image(
            painter = painterResource(
                id = R.drawable.baseline_arrow_downward_24
            ),
            contentDescription = null
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
                )
        )
    }
}

@Composable
fun EmptyCityItemContent() {
    Row {
        Image(
            painter = painterResource(
                id = R.drawable.baseline_arrow_downward_24
            ),
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
                    CityItem(isNewItemPlaceholder = true)
                    CityItem(city = "Moscow")
                }
            }
        }
    }
}