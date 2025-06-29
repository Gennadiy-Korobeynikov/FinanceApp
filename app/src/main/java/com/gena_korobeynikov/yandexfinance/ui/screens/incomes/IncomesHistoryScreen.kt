package com.gena_korobeynikov.yandexfinance.ui.screens.incomes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.gena_korobeynikov.yandexfinance.ui.components.HistoryContent
import com.gena_korobeynikov.yandexfinance.ui.viewModels.HistoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun IncomesHistoryScreen(viewModel: HistoryViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val startDate by viewModel.startDate.collectAsState()
    val endDate by viewModel.endDate.collectAsState()
    val accountId: Long = 1

    LaunchedEffect(Unit) {
        viewModel.setParams(accountId, isIncome =  true)
    }

    HistoryContent(
        viewModel = viewModel,
        startDate = startDate,
        endDate = endDate,
        uiState = uiState,
    )
}