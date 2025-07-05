package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gena_korobeynikov.yandexfinance.ui.screens.expenses.CreateExpenseScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.expenses.ExpensesScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.expenses.ExpensesHistoryScreen


fun NavGraphBuilder.expensesGraph(
    navController : NavHostController
) {
    navigation(
        startDestination = Screen.Expenses.route,
        route = Screen.ExpensesRoot.route
    ) {
        composable(Screen.Expenses.route) {
            ExpensesScreen()
        }
        composable(Screen.ExpensesHistory.route) {
            ExpensesHistoryScreen()
        }
        composable(Screen.CreateExpense.route) {
            CreateExpenseScreen(navController)
        }
    }
}