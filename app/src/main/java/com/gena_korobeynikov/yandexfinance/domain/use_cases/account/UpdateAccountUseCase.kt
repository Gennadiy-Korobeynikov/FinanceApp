package com.gena_korobeynikov.yandexfinance.domain.use_cases.account
import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository

class UpdateAccountUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(account: AccountDto): Account {
        return accountRepository.updateAccount(account)
    }
}