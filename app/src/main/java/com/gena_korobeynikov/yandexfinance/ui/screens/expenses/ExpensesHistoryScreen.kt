package com.gena_korobeynikov.yandexfinance.ui.screens.expenses

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.gena_korobeynikov.yandexfinance.ui.components.HistoryContent
import com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions.HistoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalTransactionsViewModelFactory

@Composable
fun ExpensesHistoryScreen() {
    val factory = LocalTransactionsViewModelFactory.current
    val viewModel: HistoryViewModel = viewModel(factory = factory)

    val uiState by viewModel.uiState.collectAsState()
    val startDate by viewModel.startDate.collectAsState()
    val endDate by viewModel.endDate.collectAsState()
    val accountId: Long = 1


    LaunchedEffect(Unit) {
        viewModel.setParams(accountId,isIncome =  false)
    }

    HistoryContent(
        viewModel = viewModel,
        startDate = startDate,
        endDate = endDate,
        uiState = uiState,
    )
}