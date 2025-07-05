package com.gena_korobeynikov.yandexfinance.ui.viewModels

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.di.TemporaryServiceLocator
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository
import com.gena_korobeynikov.yandexfinance.domain.use_cases.account.GetAccountUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.account.UpdateAccountUseCase
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.AccountUi


class AccountViewModel(
    private val accountRepository : AccountRepository = TemporaryServiceLocator.accountRepository,

    private val getAccountUC: GetAccountUseCase = GetAccountUseCase(accountRepository),
    private val updateAccountUC: UpdateAccountUseCase = UpdateAccountUseCase(accountRepository),
) : BaseLoadViewModel<Account, AccountUi>() {

    fun loadAccount(accountId: Long) {
        load(
            mapper = { it.toUi() },
            block = { getAccountUC(accountId) }
        )
    }

//    fun updateAccount(account : AccountDto) {
//        load(
//            mapper = {it.toUi() },
//            block = { updateAccountUC(account) }
//        )
//    }
}

