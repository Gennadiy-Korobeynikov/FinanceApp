package com.gena_korobeynikov.yandexfinance.ui.viewModels.account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.data.api.ACCOUNT_ID
import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.domain.use_cases.account.GetAccountUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.account.UpdateAccountUseCase
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.AccountUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class EditAccountViewModel @AssistedInject constructor(
    @Assisted private val accountId: Long,
    private val getAccountUC: GetAccountUseCase,
    private val updateAccountUC: UpdateAccountUseCase
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

    @AssistedFactory
    interface Factory {
        fun create(accountId: Long): EditAccountViewModel
    }
}