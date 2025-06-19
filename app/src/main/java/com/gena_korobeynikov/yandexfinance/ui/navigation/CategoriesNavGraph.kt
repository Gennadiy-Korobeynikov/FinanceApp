package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gena_korobeynikov.yandexfinance.ui.screens.CategoriesScreen


fun NavGraphBuilder.categoriesNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Categories.route,
        route = Screen.CategoriesRoot.route
    ) {
        composable(Screen.Categories.route) {
            CategoriesScreen()
        }
    }
}