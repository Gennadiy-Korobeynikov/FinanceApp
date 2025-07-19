package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.gena_korobeynikov.yandexfinance.ui.screens.account.EditAccountScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.expenses.CreateExpenseScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.expenses.EditExpenseScreen
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
            ExpensesScreen(navController)
        }
        composable(Screen.ExpensesHistory.route) {
            ExpensesHistoryScreen()
        }
        composable(Screen.CreateExpense.route) {
            CreateExpenseScreen(navController)
        }
        composable(
            route = "${Screen.EditExpense.route}/{transactionId}",
            arguments = listOf(navArgument("transactionId") { type = NavType.LongType })
        ) { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getLong("transactionId") ?: 0L
            EditExpenseScreen(
                transactionId = transactionId,
                navController = navController
            )
        }
    }
}