package com.gena_korobeynikov.yandexfinance.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.ui.states.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseLoadViewModel<D, U> : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<U>>(UiState.Loading)
    val uiState: StateFlow<UiState<U>> = _uiState.asStateFlow()

    protected fun load(mapper: (D) -> U, block: suspend () -> D) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val result = block()
                _uiState.value = UiState.Success(mapper(result))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Ошибка загрузки")
            }
        }
    }
}