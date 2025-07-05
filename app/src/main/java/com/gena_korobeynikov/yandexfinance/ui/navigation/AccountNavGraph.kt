package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.gena_korobeynikov.yandexfinance.ui.screens.account.AccountScreen
import com.gena_korobeynikov.yandexfinance.ui.screens.account.EditAccountScreen


fun NavGraphBuilder.accountNavGraph(
    navController : NavHostController
) {
    navigation(
        startDestination = Screen.Account.route,
        route = Screen.AccountRoot.route
    ) {
        composable(Screen.Account.route) {
            AccountScreen()
        }

        composable(
            route = "${Screen.EditAccount.route}/{accountId}",
            arguments = listOf(navArgument("accountId") { type = NavType.LongType })
        ) { backStackEntry ->
            val accountId = backStackEntry.arguments?.getLong("accountId") ?: 0L
            EditAccountScreen(
                accountId = accountId,
                navController = navController
            )
        }
    }
}