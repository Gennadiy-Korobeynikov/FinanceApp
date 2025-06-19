package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gena_korobeynikov.yandexfinance.ui.screens.ExpensesScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.HistoryScreen


fun NavGraphBuilder.expensesGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Expenses.route,
        route = Screen.ExpensesRoot.route
    ) {
        composable(Screen.Expenses.route) {
            ExpensesScreen()
        }
        composable(Screen.History.route) {
            HistoryScreen()
        }
    }
}