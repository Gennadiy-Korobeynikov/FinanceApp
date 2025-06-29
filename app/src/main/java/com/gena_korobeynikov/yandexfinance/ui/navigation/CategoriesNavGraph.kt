package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gena_korobeynikov.yandexfinance.ui.screens.categories.CategoriesScreen


fun NavGraphBuilder.categoriesNavGraph() {
    navigation(
        startDestination = Screen.Categories.route,
        route = Screen.CategoriesRoot.route
    ) {
        composable(Screen.Categories.route) {
            CategoriesScreen()
        }
    }
}