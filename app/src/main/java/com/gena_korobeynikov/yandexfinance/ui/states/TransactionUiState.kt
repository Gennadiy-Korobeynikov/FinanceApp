package com.gena_korobeynikov.yandexfinance.ui.states

import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.ui.models.TransactionUi

sealed class TransactionUiState {
    data object Loading : TransactionUiState()
    data class Success(val transactions: List<TransactionUi>) : TransactionUiState()
    data class Error(val message: String) : TransactionUiState()
}