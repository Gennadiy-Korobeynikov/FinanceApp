package com.gena_korobeynikov.yandexfinance.data.repo_Implementations

import android.util.Log
import com.gena_korobeynikov.yandexfinance.data.api.AccountApi
import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.dto.UpdatingAccountDto
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AccountRepositoryImpl(
    private val api: AccountApi,
    private val dispatcher: CoroutineDispatcher,
) : AccountRepository {

    override suspend fun getAccount(accountId: Long): Account = withContext(dispatcher) {
        api.getAccount(accountId).toDomain()
    }

    override suspend fun updateAccount(accountDto: AccountDto) : Account   = withContext(dispatcher) {
        try {
            val account = api.updateAccountById(
                accountDto.id,
                UpdatingAccountDto(
                    name = accountDto.name,
                    balance = accountDto.balance,
                    currency = accountDto.currency,
                )
            )
            account.toDomain()
        } catch (e: HttpException) {
            Log.e("HTTP", e.message ?: "HttpException")
            accountDto.toDomain()
        }


    }

}