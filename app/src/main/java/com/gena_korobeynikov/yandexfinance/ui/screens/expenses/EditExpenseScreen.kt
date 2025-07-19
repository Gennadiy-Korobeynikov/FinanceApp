package com.gena_korobeynikov.yandexfinance.ui.screens.expenses

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.gena_korobeynikov.yandexfinance.ui.components.TransactionEditingContent


@Composable
fun EditExpenseScreen(
    transactionId: Long,
    navController: NavController
) {
    TransactionEditingContent(transactionId,navController, isIncome = false)
}
