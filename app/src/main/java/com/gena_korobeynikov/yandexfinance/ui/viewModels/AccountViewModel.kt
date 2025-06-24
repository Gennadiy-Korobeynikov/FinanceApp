package com.gena_korobeynikov.yandexfinance.ui.viewModels

import com.gena_korobeynikov.yandexfinance.domain.Account
import com.gena_korobeynikov.yandexfinance.domain.AccountRepository


class AccountViewModel(
    private val repository: AccountRepository
) : BaseLoadViewModel<Account>() {

    fun loadAccount(accountId: Long) {
        load {
            repository.getAccount(accountId)
        }
    }
}

