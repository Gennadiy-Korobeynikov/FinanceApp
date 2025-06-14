package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gena_korobeynikov.yandexfinance.ui.screens.AccountScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.ExpenseCategoriesScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.ExpensesScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.IncomesScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.SettingsScreen


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Expenses.route,

    ) {
        composable(Screen.Expenses.route) { ExpensesScreen() }
        composable(Screen.Incomes.route) { IncomesScreen() }
        composable(Screen.Account.route) { AccountScreen() }
        composable(Screen.Categories.route) { ExpenseCategoriesScreen() }
        composable(Screen.Settings.route) { SettingsScreen() }

    }
}