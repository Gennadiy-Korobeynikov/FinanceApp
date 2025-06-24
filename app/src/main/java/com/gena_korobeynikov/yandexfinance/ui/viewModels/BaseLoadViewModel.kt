package com.gena_korobeynikov.yandexfinance.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseLoadViewModel<T> : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<T>>(UiState.Loading)
    val uiState: StateFlow<UiState<T>> = _uiState.asStateFlow()

    protected fun load(block: suspend () -> T) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val result = block()
                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Ошибка загрузки")
            }
        }
    }
}