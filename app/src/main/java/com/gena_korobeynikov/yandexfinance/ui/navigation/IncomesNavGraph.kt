package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gena_korobeynikov.yandexfinance.ui.screens.incomes.IncomesHistoryScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.incomes.IncomesScreen


fun NavGraphBuilder.incomesNavGraph() {
    navigation(
        startDestination = Screen.Incomes.route,
        route = Screen.IncomesRoot.route
    ) {
        composable(Screen.Incomes.route) {
            IncomesScreen()
        }
        composable(Screen.IncomesHistory.route) {
            IncomesHistoryScreen()
        }
    }
}