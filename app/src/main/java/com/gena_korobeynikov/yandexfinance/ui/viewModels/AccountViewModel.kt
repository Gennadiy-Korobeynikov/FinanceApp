package com.gena_korobeynikov.yandexfinance.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.domain.AccountRepository
import com.gena_korobeynikov.yandexfinance.domain.TransactionsRepository
import com.gena_korobeynikov.yandexfinance.ui.states.AccountUiState
import com.gena_korobeynikov.yandexfinance.ui.states.TransactionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AccountViewModel(
    private val repository: AccountRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AccountUiState>(AccountUiState.Loading)
    val uiState: StateFlow<AccountUiState> = _uiState.asStateFlow()

    fun loadAccount(accountId: Long) {
        viewModelScope.launch {
            try {
                _uiState.value = AccountUiState.Loading
                val transactions = repository.getAccount(accountId)
                _uiState.value = AccountUiState.Success(transactions)
            } catch (e: Exception) {
                _uiState.value = AccountUiState.Error(e.message ?: "Ошибка загрузки")
            }
        }
    }
}