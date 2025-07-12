package com.gena_korobeynikov.yandexfinance.ui.viewModels_factories

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider
import com.gena_korobeynikov.yandexfinance.ui.viewModels.account.EditAccountViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions.EditTransactionViewModel

val LocalAccountViewModelFactory = staticCompositionLocalOf<ViewModelProvider.Factory> {
    error("ViewModelFactory not provided")
}

val LocalCategoriesViewModelFactory = staticCompositionLocalOf<ViewModelProvider.Factory> {
    error("ViewModelFactory not provided")
}

val LocalTransactionsViewModelFactory = staticCompositionLocalOf<ViewModelProvider.Factory> {
    error("ViewModelFactory not provided")
}

val LocalEditAccountVMFactory = staticCompositionLocalOf<EditAccountViewModel.Factory> {
    error("EditAccountViewModel.Factory not provided")
}

val LocalEditTransactionVMFactory = staticCompositionLocalOf<EditTransactionViewModel.Factory> {
    error("EditTransactionViewModel.Factory not provided")
}