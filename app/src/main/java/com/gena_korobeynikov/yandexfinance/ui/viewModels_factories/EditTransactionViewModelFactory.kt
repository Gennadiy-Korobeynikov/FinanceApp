package com.gena_korobeynikov.yandexfinance.ui.viewModels_factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gena_korobeynikov.yandexfinance.ui.viewModels.account.EditAccountViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions.EditTransactionViewModel

class EditTransactionViewModelFactory (
    private val assistedFactory: EditTransactionViewModel.Factory,
    private val transactionId : Long
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditAccountViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return assistedFactory.create( transactionId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }