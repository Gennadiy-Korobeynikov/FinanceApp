package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gena_korobeynikov.yandexfinance.ui.screens.settings.SettingsScreen


fun NavGraphBuilder.settingsNavGraph() {
    navigation(
        startDestination = Screen.Settings.route,
        route = Screen.SettingsRoot.route
    ) {
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}