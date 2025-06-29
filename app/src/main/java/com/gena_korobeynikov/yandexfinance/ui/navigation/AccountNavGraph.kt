package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gena_korobeynikov.yandexfinance.ui.screens.account.AccountScreen


fun NavGraphBuilder.accountNavGraph() {
    navigation(
        startDestination = Screen.Account.route,
        route = Screen.AccountRoot.route
    ) {
        composable(Screen.Account.route) {
            AccountScreen()
        }
    }
}