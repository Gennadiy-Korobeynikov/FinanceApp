package com.gena_korobeynikov.yandexfinance.ui.navigation

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavHostController
import com.gena_korobeynikov.yandexfinance.R

sealed class Screen(
    val route: String,
    val root: Screen = Parentless,
    @StringRes val titleRes: Int,
    @StringRes val navBarItemTitleRes: Int? = null,
    @DrawableRes val navBarIconRes: Int? = null,
    @DrawableRes val topBarBtnIconRes: Int? = null,
    val topBarBtnAction: ((NavHostController) -> Unit)? = null,
    val addBtnAction: (() -> Unit)? = null
) {
    // Специальный placeholder — для экранов без родителя
    data object Parentless : Screen("", titleRes = 0)

    // Корневые маршруты
    data object ExpensesRoot : Screen("expenses_root", titleRes = R.string.expenses_title)
    data object IncomesRoot : Screen("incomes_root", titleRes = R.string.incomes_title)
    data object AccountRoot : Screen("account_root", titleRes = R.string.account_title)
    data object CategoriesRoot : Screen("categories_root", titleRes = R.string.expense_categories_title)
    data object SettingsRoot : Screen("settings_root", titleRes = R.string.settings_title)

    // Основные экраны
    data object Expenses : Screen(
        route = "expenses",
        root = ExpensesRoot,
        titleRes = R.string.expenses_title,
        navBarItemTitleRes = R.string.expenses,
        navBarIconRes = R.drawable.ic_navbar_expenses,
        topBarBtnIconRes = R.drawable.ic_history,
        topBarBtnAction = { navController ->
            navController.navigate("history")
        },
        addBtnAction = { /* TODO: add new expense */ }
    )

    data object History : Screen(
        route = "history",
        root = ExpensesRoot,
        titleRes = R.string.history_title,
        topBarBtnIconRes = R.drawable.ic_plus,
        topBarBtnAction = { /* TODO */ }
    )

    data object Incomes : Screen(
        route = "incomes",
        root = IncomesRoot,
        titleRes = R.string.incomes_title,
        navBarItemTitleRes = R.string.incomes,
        navBarIconRes = R.drawable.ic_navbar_incomes,
        topBarBtnIconRes = R.drawable.ic_history,
        topBarBtnAction = { navController ->
            Log.i("TopBar", "Incomes button clicked")
        },
        addBtnAction = { /* TODO: add new income */ }
    )

    data object Account : Screen(
        route = "account",
        root = AccountRoot,
        titleRes = R.string.account_title,
        navBarItemTitleRes = R.string.account,
        navBarIconRes = R.drawable.ic_navbar_account,
        topBarBtnIconRes = R.drawable.ic_edit,
        topBarBtnAction = { navController ->
            Log.i("TopBar", "Edit account")
        },
        addBtnAction = { /* TODO */ }
    )

    data object Categories : Screen(
        route = "expense_categories",
        root = CategoriesRoot,
        titleRes = R.string.expense_categories_title,
        navBarItemTitleRes = R.string.expense_categories,
        navBarIconRes = R.drawable.ic_navbar_categories
    )

    data object Settings : Screen(
        route = "settings",
        root = SettingsRoot,
        titleRes = R.string.settings_title,
        navBarItemTitleRes = R.string.settings,
        navBarIconRes = R.drawable.ic_navbar_settings
    )

    companion object {
        // Только вкладки нижней навигации
        val allNavBar = listOf(Expenses, Incomes, Account, Categories, Settings)

        // Все экраны приложения
        val all = listOf(
            ExpensesRoot, Expenses, History,
            IncomesRoot, Incomes,
            AccountRoot, Account,
            CategoriesRoot, Categories,
            SettingsRoot, Settings
        )

        fun fromRoute(route: String?): Screen? =
            all.find { it.route == route }

        //Определить корневой экран по маршруту
        fun rootOf(route: String?): Screen? {
            val screen = fromRoute(route)
            return generateSequence(screen) {
                if (it.root == Parentless || it.root == it) null else it.root
            }.lastOrNull()
        }
    }
}
