package com.gena_korobeynikov.yandexfinance.ui.viewModels

import com.gena_korobeynikov.yandexfinance.di.TemporaryServiceLocator
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.use_cases.GetAccountUseCase
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.AccountUi


class AccountViewModel(
    private val getAccount : GetAccountUseCase =
        GetAccountUseCase(TemporaryServiceLocator.accountRepository)
) : BaseLoadViewModel<Account, AccountUi>() {

    fun loadAccount(accountId: Long) {
        load(
            mapper = { it.toUi() },
            block = { getAccount(accountId) }
        )
    }
}

