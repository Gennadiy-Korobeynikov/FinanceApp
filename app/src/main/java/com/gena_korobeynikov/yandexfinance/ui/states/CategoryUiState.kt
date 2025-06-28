package com.gena_korobeynikov.yandexfinance.ui.states

import com.gena_korobeynikov.yandexfinance.domain.models.Category
import com.gena_korobeynikov.yandexfinance.ui.models.CategoryUi

sealed class CategoryUiState {
    data object Loading : CategoryUiState()
    data class Success(val categories: List<CategoryUi>) : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}