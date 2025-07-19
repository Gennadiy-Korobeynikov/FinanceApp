package com.gena_korobeynikov.yandexfinance.domain.use_cases.account
import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.di.scopes.AccountScope
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountRepository
import javax.inject.Inject

@AccountScope
class UpdateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(account: AccountDto) {
        accountRepository.updateAccount(account)
    }
}