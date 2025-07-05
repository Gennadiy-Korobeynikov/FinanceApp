package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavHostController
import com.gena_korobeynikov.yandexfinance.R

object ScreenRoutes {
    const val Expenses = "expenses"
    const val ExpensesHistory = "expenses_history"
    const val CreateExpense = "create_expense"

    const val Incomes = "incomes"
    const val IncomesHistory = "incomes_history"
    const val CreateIncome = "create_income"

    const val Account = "account"
    const val EditAccount = "edit_account"

    const val Categories = "expense_categories"

    const val Settings = "settings"
}



sealed class Screen(
    val route: String,
    val root: Screen = Parentless,
    @StringRes val titleRes: Int,
    @StringRes val navBarItemTitleRes: Int? = null,
    @DrawableRes val navBarIconRes: Int? = null,
    @DrawableRes val topBarBtnIconRes: Int? = null,
    val topBarBtnAction: ((NavHostController) -> Unit)? = null,
    val addBtnAction: ((NavHostController) -> Unit)? = null
) {
    // Специальный placeholder — для экранов без родителя
    data object Parentless : Screen("", titleRes = 0)

    // Корневые маршруты
    data object ExpensesRoot : Screen("expenses_root", titleRes = R.string.expenses_title)
    data object IncomesRoot : Screen("incomes_root", titleRes = R.string.incomes_title)
    data object AccountRoot : Screen("account_root", titleRes = R.string.account_title)
    data object CategoriesRoot : Screen("categories_root", titleRes = R.string.expense_categories_title)
    data object SettingsRoot : Screen("settings_root", titleRes = R.string.settings_title)

    // Expenses
    data object Expenses : Screen(
        route = ScreenRoutes.Expenses,
        root = ExpensesRoot,
        titleRes = R.string.expenses_title,
        navBarItemTitleRes = R.string.expenses,
        navBarIconRes = R.drawable.ic_navbar_expenses,
        topBarBtnIconRes = R.drawable.ic_history,
        topBarBtnAction = { navController ->
            navController.navigate(ScreenRoutes.ExpensesHistory)
        },
        addBtnAction = { navController ->
            navController.navigate(ScreenRoutes.CreateExpense)
        }
    )

    data object ExpensesHistory : Screen(
        route = ScreenRoutes.ExpensesHistory,
        root = ExpensesRoot,
        titleRes = R.string.history_title,
        topBarBtnIconRes = R.drawable.ic_analyze,
        topBarBtnAction = { /* TODO */ }
    )

    data object CreateExpense : Screen(
        route = ScreenRoutes.CreateExpense,
        root = ExpensesRoot,
        titleRes = R.string.expenses,
    )

    // Incomes
    data object Incomes : Screen(
        route = ScreenRoutes.Incomes,
        root = IncomesRoot,
        titleRes = R.string.incomes_title,
        navBarItemTitleRes = R.string.incomes,
        navBarIconRes = R.drawable.ic_navbar_incomes,
        topBarBtnIconRes = R.drawable.ic_history,
        topBarBtnAction = { navController ->
            navController.navigate(ScreenRoutes.IncomesHistory)
        },
        addBtnAction = { navController ->
            navController.navigate(ScreenRoutes.CreateIncome)
        }
    )

    data object IncomesHistory : Screen(
        route = ScreenRoutes.IncomesHistory,
        root = IncomesRoot,
        titleRes = R.string.history_title,
        topBarBtnIconRes = R.drawable.ic_analyze,
        topBarBtnAction = { /* TODO */ }
    )

    data object CreateIncome : Screen(
        route = ScreenRoutes.CreateIncome,
        root = IncomesRoot,
        titleRes = R.string.incomes,
    )


    // Account
    data object Account : Screen(
        route = ScreenRoutes.Account,
        root = AccountRoot,
        titleRes = R.string.account_title,
        navBarItemTitleRes = R.string.account,
        navBarIconRes = R.drawable.ic_navbar_account,
        topBarBtnIconRes = R.drawable.ic_edit,
        topBarBtnAction = { navController ->
            navController.navigate("${ScreenRoutes.EditAccount}/1")
        },
    )

    data object EditAccount : Screen(
        route = ScreenRoutes.EditAccount,
        root = AccountRoot,
        titleRes = R.string.account_title,
    )

    // Categories
    data object Categories : Screen(
        route = ScreenRoutes.Categories,
        root = CategoriesRoot,
        titleRes = R.string.expense_categories_title,
        navBarItemTitleRes = R.string.expense_categories,
        navBarIconRes = R.drawable.ic_navbar_categories
    )


    // Settings
    data object Settings : Screen(
        route = ScreenRoutes.Settings,
        root = SettingsRoot,
        titleRes = R.string.settings_title,
        navBarItemTitleRes = R.string.settings,
        navBarIconRes = R.drawable.ic_navbar_settings
    )


    // Common
    companion object {
        // Только вкладки нижней навигации
        val allNavBar = listOf(Expenses, Incomes, Account, Categories, Settings)

        // Все экраны приложения
        val all = listOf(
            ExpensesRoot, Expenses, ExpensesHistory, CreateExpense,
            IncomesRoot, Incomes, IncomesHistory, CreateIncome,
            AccountRoot, Account, EditAccount,
            CategoriesRoot, Categories,
            SettingsRoot, Settings
        )

        fun fromRoute(route: String?): Screen? =
            all.find { screen ->
                println("Comparing: route=$route, screen.route=${screen.route}, substringBefore=${route?.substringBefore("/{")}")

                route == screen.route ||
                        (route != null && route.contains("{") && screen.route.startsWith(route.substringBefore("/{")) == true)
            }
        //Определить корневой экран по маршруту
        fun rootOf(route: String?): Screen? {
            val screen = fromRoute(route)
            return generateSequence(screen) {
                if (it.root == Parentless || it.root == it) null else it.root
            }.lastOrNull()
        }
    }
}
