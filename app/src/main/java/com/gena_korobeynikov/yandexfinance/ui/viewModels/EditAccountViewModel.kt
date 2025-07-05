package com.gena_korobeynikov.yandexfinance.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.data.api.ACCOUNT_ID
import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.di.TemporaryServiceLocator
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository
import com.gena_korobeynikov.yandexfinance.domain.use_cases.account.GetAccountUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.account.UpdateAccountUseCase
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.AccountUi
import kotlinx.coroutines.launch

class EditAccountViewModel(
    accountId: Long ,
    private val accountRepository: AccountRepository = TemporaryServiceLocator.accountRepository,

    private val getAccountUC: GetAccountUseCase = GetAccountUseCase(accountRepository),
    private val updateAccountUC: UpdateAccountUseCase = UpdateAccountUseCase(accountRepository),
) : ViewModel() {

    var account by mutableStateOf<AccountUi?>(null)
        private set

    init {
        viewModelScope.launch {
            account = getAccountUC(accountId).toUi()
        }
    }

    fun updateAccount(newName: String, newBalance: String, newCurrency: String) {
        val account = AccountDto(
            id = ACCOUNT_ID,
            name = newName,
            balance = newBalance.replace(',','.'),
            currency = newCurrency,
        )
        viewModelScope.launch {
            updateAccountUC(account)
        }
    }
}