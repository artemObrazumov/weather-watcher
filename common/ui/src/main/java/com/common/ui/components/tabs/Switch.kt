package com.common.ui.components.tabs

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.common.ui.theme.WeatherWatcherTheme

@Composable
fun Switch(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (index: Int) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(8.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
    ) {
        val maxWidth = this.maxWidth
        val tabWidth = maxWidth / tabs.size

        val indicatorOffset by animateDpAsState(
            targetValue = tabWidth * selectedIndex,
            animationSpec = tween(durationMillis = 250),
            label = ""
        )

        Box(
            modifier = Modifier
                .padding(
                    start = indicatorOffset + 4.dp,
                    end = 4.dp,
                    top = 4.dp,
                    bottom = 4.dp
                )
                .width(tabWidth)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .fillMaxHeight()
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            tabs.forEachIndexed { index, text ->
                Box(
                    modifier = Modifier
                        .width(tabWidth)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null,
                            onClick = {
                                onTabSelected(index)
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        modifier = Modifier
                            .alpha( if(selectedIndex == index) 1f else 0.5f ),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}