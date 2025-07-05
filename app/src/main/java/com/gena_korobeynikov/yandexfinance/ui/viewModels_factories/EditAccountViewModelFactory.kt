package com.gena_korobeynikov.yandexfinance.ui.viewModels_factories

import android.accounts.Account
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gena_korobeynikov.yandexfinance.ui.viewModels.EditAccountViewModel

class EditAccountViewModelFactory (
    private val accountId : Long
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditAccountViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EditAccountViewModel( accountId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }