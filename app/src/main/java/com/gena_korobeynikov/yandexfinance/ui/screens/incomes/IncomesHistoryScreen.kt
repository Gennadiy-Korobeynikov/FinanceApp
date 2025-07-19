package com.gena_korobeynikov.yandexfinance.ui.screens.incomes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gena_korobeynikov.yandexfinance.data.api.ACCOUNT_ID
import com.gena_korobeynikov.yandexfinance.ui.components.HistoryContent
import com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions.HistoryViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalTransactionsViewModelFactory

@Composable
fun IncomesHistoryScreen() {
    val factory = LocalTransactionsViewModelFactory.current
    val viewModel: HistoryViewModel = viewModel(factory = factory)

    val uiState by viewModel.uiState.collectAsState()
    val startDate by viewModel.startDate.collectAsState()
    val endDate by viewModel.endDate.collectAsState()
    val accountId: Long = ACCOUNT_ID

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