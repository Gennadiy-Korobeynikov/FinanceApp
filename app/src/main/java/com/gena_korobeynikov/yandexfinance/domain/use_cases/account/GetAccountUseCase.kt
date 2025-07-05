package com.gena_korobeynikov.yandexfinance.domain.use_cases.account
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository

class GetAccountUseCase(
    private val repository: AccountRepository,
) {
    suspend operator fun invoke( accountId: Long, ): Account {
        return repository.getAccount(accountId)
    }
}