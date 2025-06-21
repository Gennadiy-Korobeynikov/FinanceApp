package com.gena_korobeynikov.yandexfinance.ui.states

import com.gena_korobeynikov.yandexfinance.domain.Transaction

sealed class TransactionUiState {
    data object Loading : TransactionUiState()
    data class Success(val transactions: List<Transaction>) : TransactionUiState()
    data class Error(val message: String) : TransactionUiState()
}