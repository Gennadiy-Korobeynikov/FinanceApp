package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.gena_korobeynikov.yandexfinance.ui.screens.incomes.CreateIncomeScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.incomes.EditIncomeScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.incomes.IncomesHistoryScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.incomes.IncomesScreen


fun NavGraphBuilder.incomesNavGraph(
    navController : NavHostController
) {
    navigation(
        startDestination = Screen.Incomes.route,
        route = Screen.IncomesRoot.route
    ) {
        composable(Screen.Incomes.route) {
            IncomesScreen(navController)
        }
        composable(Screen.IncomesHistory.route) {
            IncomesHistoryScreen()
        }
        composable(Screen.CreateIncome.route) {
            CreateIncomeScreen(navController)
        }
        composable(
            route = "${Screen.EditIncome.route}/{transactionId}",
            arguments = listOf(navArgument("transactionId") { type = NavType.LongType })
        ) { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getLong("transactionId") ?: 0L
            EditIncomeScreen(
                transactionId = transactionId,
                navController = navController
            )
        }
    }
}