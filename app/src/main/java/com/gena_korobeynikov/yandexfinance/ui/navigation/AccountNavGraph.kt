package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gena_korobeynikov.yandexfinance.ui.screens.AccountScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.ExpensesScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.HistoryScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.IncomesScreen


fun NavGraphBuilder.accountNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Account.route,
        route = Screen.AccountRoot.route
    ) {
        composable(Screen.Account.route) {
            AccountScreen()
        }
    }
}