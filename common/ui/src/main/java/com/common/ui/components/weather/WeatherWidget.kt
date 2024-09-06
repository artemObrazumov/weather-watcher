package com.common.ui.components.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemobrazumov.ui.R
import com.common.ui.components.weather.utils.toWeatherUI
import com.common.ui.theme.LocalPaddings
import com.common.ui.theme.WeatherWatcherPaddings
import com.common.ui.theme.WeatherWatcherTheme
import com.weather.domain.models.weather.Weather

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WeatherWidget(
    weather: WeatherWidgetUI,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = Brush.linearGradient(
                    listOf(Color.Magenta, Color.Blue)
                )
            )
            .padding(WeatherWatcherTheme.paddings.medium)
            .fillMaxWidth()
    ) {
        var opened by remember {
            mutableStateOf(false)
        }
        val arrowAngle by animateFloatAsState(
            targetValue = if (opened) 180f else 0f,
            label = "arrow"
        )
        Column(modifier = Modifier.padding(bottom = WeatherWatcherTheme.paddings.medium)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Place,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.medium))
                Text(
                    text = weather.city,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.medium))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_thermostat_24),
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.medium))
                Text(
                    text = weather.temperature,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Text(
                text = weather.temperatureFeels,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.medium))
            val interactionSource = remember { MutableInteractionSource() }
            Row(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { opened = !opened },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (!opened) "Показать подробности" else "Скрыть подробности",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.rotate(arrowAngle)
                )
            }
            AnimatedVisibility(visible = opened) {
                Column {
                    Spacer(modifier = Modifier.height(WeatherWatcherTheme.paddings.medium))
                    Text(
                        text = "Температура",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.baseline_thermostat_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.medium))
                        FlowRow {
                            Text(
                                text = weather.temperature,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.large))
                            Text(
                                text = weather.temperatureFeels,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.large))
                            Text(
                                text = weather.temperatureRange,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    WeatherWidgetHorizontalDivider()
                    Text(
                        text = "Ветер",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.wind),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.medium))
                        FlowRow {
                            Text(
                                text = weather.windSpeed,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.large))
                            Icon(
                                painter = painterResource(R.drawable.baseline_arrow_downward_24),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(18.dp)
                                    .rotate(weather.windDirectionDegrees)
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.small))
                            Text(
                                text = weather.windDirection,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.large))
                            Text(
                                text = weather.windGust,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    WeatherWidgetHorizontalDivider()
                    Text(
                        text = "Давление",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.baseline_compress_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.medium))
                        FlowRow {
                            Text(
                                text = weather.pressure,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.large))
                            Text(
                                text = weather.pressureGroundLevel,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.large))
                            Text(
                                text = weather.pressureSeaLevel,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    WeatherWidgetHorizontalDivider()
                    Text(
                        text = "Прочее",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row {
                        FlowRow {
                            Icon(
                                painter = painterResource(R.drawable.baseline_cloud_24),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.medium))
                            Text(
                                text = weather.clouds,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.large))
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(WeatherWatcherTheme.paddings.medium))
                            Text(
                                text = weather.visibility,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WeatherWidgetHorizontalDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(WeatherWatcherTheme.paddings.medium),
        color = Color.White
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WeatherWidgetPreview() {
    CompositionLocalProvider(
        LocalPaddings provides WeatherWatcherPaddings(
            small = 2.dp,
            default = 4.dp,
            medium = 8.dp,
            large = 16.dp,
            extraLarge = 24.dp
        )
    ) {
        val weather = Weather(
            temperature = 23,
            temperatureFeels = 20,
            temperatureMin = 20,
            temperatureMax = 25,
            pressure = 10,
            humidity = 8,
            pressureSeaLevel = 13,
            pressureGroundLevel = 10,
            windSpeed = 1.8,
            windDirectionDegrees = 37,
            windGust = 5.5,
            clouds = 1,
            visibility = 4,
            city = "Москва"
        ).toWeatherUI()
        WeatherWidget(
            modifier = Modifier.padding(16.dp),
            weather = weather
        )
    }
}