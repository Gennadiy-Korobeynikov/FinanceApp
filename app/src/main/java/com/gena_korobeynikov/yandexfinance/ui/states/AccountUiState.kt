package com.gena_korobeynikov.yandexfinance.ui.states

import com.gena_korobeynikov.yandexfinance.domain.Account
import com.gena_korobeynikov.yandexfinance.domain.Transaction

sealed class AccountUiState {
    data object Loading : AccountUiState()
    data class Success(val account: Account) : AccountUiState()
    data class Error(val message: String) : AccountUiState()
}