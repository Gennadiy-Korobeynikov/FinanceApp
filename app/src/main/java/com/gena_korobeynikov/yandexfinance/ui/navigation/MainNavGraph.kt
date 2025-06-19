package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "expenses_root"
    ) {
        expensesGraph(navController)
        incomesNavGraph(navController)
        accountNavGraph(navController)
        categoriesNavGraph(navController)
        settingsNavGraph(navController)
    }
}