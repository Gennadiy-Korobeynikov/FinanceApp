package com.gena_korobeynikov.yandexfinance.ui.viewModels.account

import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.use_cases.account.GetAccountUseCase
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.AccountUi
import com.gena_korobeynikov.yandexfinance.ui.viewModels.BaseLoadViewModel
import javax.inject.Inject


class AccountViewModel @Inject constructor(
    private val getAccountUC: GetAccountUseCase
) : BaseLoadViewModel<Account, AccountUi>() {

    fun loadAccount(accountId: Long) {
        load(
            mapper = { it.toUi() },
            block = { getAccountUC(accountId) }
        )
    }
}

