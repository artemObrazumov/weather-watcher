package com.main.presentation.screens.city_info.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.setInputMerger
import com.main.presentation.screens.city_info.state_hoisting.CityInfoScreenAction
import com.main.presentation.screens.city_info.state_hoisting.CityMonitoringState
import com.main.presentation.work.CityMonitoringWorker
import com.main.presentation.work.CityMonitoringWorker.Companion.WORKER_CITY_ID

@Composable
fun CityMonitoringPage(
    state: CityMonitoringState,
    modifier: Modifier = Modifier,
    onAction: (CityInfoScreenAction) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        Button(onClick = {
            val data = Data.Builder()
                .apply { putInt(WORKER_CITY_ID, 1) }
                .build()
            val saveLogsWorker = OneTimeWorkRequestBuilder<CityMonitoringWorker>()
                .apply { setInputData(data) }
                .build()
            val workManager = WorkManager.getInstance(context.applicationContext)
            workManager.enqueue(saveLogsWorker)
        }) {
            Text(text = "Записать логи")
        }
    }
}