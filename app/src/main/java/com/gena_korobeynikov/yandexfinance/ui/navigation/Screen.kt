package com.gena_korobeynikov.yandexfinance.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gena_korobeynikov.yandexfinance.R

sealed class Screen(
    val route: String,
    @StringRes val titleRes: Int,
    @StringRes val navBarItemTitleRes: Int,
    @DrawableRes val iconRes: Int,
    val addBtnAction : (() -> Unit)? = null
)
{
    data object Expenses : Screen("expenses",R.string.expenses_title, R.string.expenses, R.drawable.ic_navbar_expenses, {})
    data object Incomes : Screen("incomes",R.string.incomes_title, R.string.incomes, R.drawable.ic_navbar_incomes, {})
    data object Account : Screen("account",R.string.account_title, R.string.account, R.drawable.ic_navbar_account, {})
    data object Categories : Screen("expense_categories" ,R.string.expense_categories_title, R.string.expense_categories, R.drawable.ic_navbar_categories)
    data object Settings : Screen("settings",R.string.settings_title, R.string.settings, R.drawable.ic_navbar_settings)

    companion object {
        val all = listOf(Expenses ,Incomes, Account, Categories, Settings)

        fun fromRoute(route: String?): Screen? =
            all.find { it.route == route }
    }
}