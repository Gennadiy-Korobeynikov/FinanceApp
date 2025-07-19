package com.gena_korobeynikov.yandexfinance.data.repo_implementations.account_repos

import android.util.Log
import com.gena_korobeynikov.yandexfinance.data.api.AccountApi
import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.dto.UpdatingAccountDto
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain.toDomain
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRemoteRepositoryImpl @Inject constructor(
    private val api: AccountApi,
    private val dispatcher: CoroutineDispatcher,
) : AccountRemoteRepository {

    override suspend fun getAccount(id: Long): Account = withContext(dispatcher) {
        api.getAccount(id).toDomain()
    }

    override suspend fun updateAccount(accountDto: AccountDto) : Account = withContext(dispatcher) {
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