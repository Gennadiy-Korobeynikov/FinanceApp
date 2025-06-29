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
        expensesGraph()
        incomesNavGraph()
        accountNavGraph()
        categoriesNavGraph()
        settingsNavGraph()
    }
}


fun NavHostController.safeNavigate(root: Screen) {
    val currentRoot = Screen.rootOf(currentBackStackEntry?.destination?.route)

    // Если уже на этом root-графе — очищаем вложенную навигацию до начального экрана
    if (currentRoot == root) {
        val startChild = Screen.all.find { it.root == root && it != root }
        startChild?.let {
            navigate(it.route) {
                popUpTo(root.route) {
                    inclusive = false
                }
                launchSingleTop = true
            }
        }
    } else {
        // Переход к root-графу
        navigate(root.route) {
            popUpTo(Screen.ExpensesRoot.route) {  // здесь можно использовать root.route
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}