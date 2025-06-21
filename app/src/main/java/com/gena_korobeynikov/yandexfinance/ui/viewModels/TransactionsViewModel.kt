package com.gena_korobeynikov.yandexfinance.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.domain.TransactionsRepository
import com.gena_korobeynikov.yandexfinance.ui.states.TransactionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class TransactionsViewModel(
    private val repository: TransactionsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TransactionUiState>(TransactionUiState.Loading)
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()

    fun loadTransactions(accountId: Long, startDate: String? = null, endDate: String? = null) {
        viewModelScope.launch {
            try {
                _uiState.value = TransactionUiState.Loading
                val transactions = repository.getTransactionsForPeriod(accountId, startDate, endDate)
                _uiState.value = TransactionUiState.Success(transactions)
            } catch (e: Exception) {
                _uiState.value = TransactionUiState.Error(e.message ?: "Ошибка загрузки")
            }
        }
    }
}