package com.gena_korobeynikov.yandexfinance.domain.use_cases.account
import com.gena_korobeynikov.yandexfinance.di.scopes.AccountScope
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository
import javax.inject.Inject

@AccountScope
class GetAccountUseCase @Inject constructor(
    private val repository: AccountRepository,
) {
    suspend operator fun invoke( accountId: Long, ): Account {
        return repository.getAccount(accountId)
    }
}