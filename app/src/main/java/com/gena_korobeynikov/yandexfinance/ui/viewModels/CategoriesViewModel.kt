package com.gena_korobeynikov.yandexfinance.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.domain.CategoriesRepository
import com.gena_korobeynikov.yandexfinance.domain.TransactionsRepository
import com.gena_korobeynikov.yandexfinance.ui.states.CategoryUiState
import com.gena_korobeynikov.yandexfinance.ui.states.TransactionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CategoriesViewModel(
    private val repository: CategoriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    fun loadCategories() {
        viewModelScope.launch {
            try {
                _uiState.value = CategoryUiState.Loading
                val categories = repository.getCategories()
                _uiState.value = CategoryUiState.Success(categories)
            } catch (e: Exception) {
                _uiState.value = CategoryUiState.Error(e.message ?: "Ошибка загрузки")
            }
        }
    }
}