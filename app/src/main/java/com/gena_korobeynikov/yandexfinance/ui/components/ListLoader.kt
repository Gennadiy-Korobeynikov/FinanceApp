package com.gena_korobeynikov.yandexfinance.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gena_korobeynikov.yandexfinance.ui.states.UiState


@Composable
fun <T> ListLoader(
    uiState: UiState<T>,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    },
    errorContent: @Composable (String) -> Unit = { message ->
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Ошибка: $message", color = Color.Red)
        }
    },
    successContent: @Composable (T) -> Unit
) {
    when (uiState) {
        is UiState.Loading -> loadingContent()
        is UiState.Error -> errorContent(uiState.message)
        is UiState.Success -> successContent(uiState.data)
    }
}
