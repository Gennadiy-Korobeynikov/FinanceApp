package com.gena_korobeynikov.yandexfinance.ui.states

import com.gena_korobeynikov.yandexfinance.domain.Category

sealed class CategoryUiState {
    data object Loading : CategoryUiState()
    data class Success(val categories: List<Category>) : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}