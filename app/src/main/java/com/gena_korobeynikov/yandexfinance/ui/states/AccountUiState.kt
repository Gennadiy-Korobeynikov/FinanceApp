package com.gena_korobeynikov.yandexfinance.ui.states

import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.ui.models.AccountUi

sealed class AccountUiState {
    data object Loading : AccountUiState()
    data class Success(val account: AccountUi) : AccountUiState()
    data class Error(val message: String) : AccountUiState()
}