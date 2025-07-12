package com.gena_korobeynikov.yandexfinance

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.CompositionLocalProvider
import com.gena_korobeynikov.yandexfinance.di.components.AccountComponent
import com.gena_korobeynikov.yandexfinance.di.components.CategoriesComponent
import com.gena_korobeynikov.yandexfinance.di.components.TransactionsComponent
import com.gena_korobeynikov.yandexfinance.ui.screens.MainScreen
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalAccountViewModelFactory
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalCategoriesViewModelFactory
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalEditAccountVMFactory
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalEditTransactionVMFactory
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalTransactionsViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private lateinit var accountsComponent: AccountComponent
    private lateinit var transactionsComponent: TransactionsComponent
    private lateinit var categoriesComponent: CategoriesComponent

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val appComponent = (application as YandexFinanceApp).appComponent
        appComponent.inject(this)

        accountsComponent = appComponent.accountsComponent().create()
        transactionsComponent = appComponent.transactionsComponent().create()
        categoriesComponent = appComponent.categoriesComponent().create()


        setContent {
            CompositionLocalProvider(
                LocalCategoriesViewModelFactory provides categoriesComponent.viewModelFactory(),
                LocalTransactionsViewModelFactory provides transactionsComponent.viewModelFactory(),
                LocalAccountViewModelFactory provides accountsComponent.viewModelFactory(),
                LocalEditAccountVMFactory provides accountsComponent.editAccountViewModelFactory(),
                LocalEditTransactionVMFactory provides transactionsComponent.editTransactionViewModelFactory(),
            ) {
                MainScreen()
            }

        }
    }
}