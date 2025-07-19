package com.gena_korobeynikov.yandexfinance.data.repo_implementations

import android.util.Log
import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.local.dao.AccountDao
import com.gena_korobeynikov.yandexfinance.data.mappers.toDomain.toDomain
import com.gena_korobeynikov.yandexfinance.data.mappers.toEntity.toEntity
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.repos.account.AccountLocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountLocalRepositoryImpl @Inject constructor(
    private val dao: AccountDao,
    private val dispatcher: CoroutineDispatcher,
) : AccountLocalRepository {

    override suspend fun getAccount(id: Long): Account = withContext(dispatcher) {
        dao.getAccountById(id)?.toDomain()
            ?: throw NoSuchElementException("Account with id $id not found")
    }

    override suspend fun upsertAccount(account: AccountDto) = withContext(dispatcher) {
        try {
            dao.updateAccount(account.toEntity())
        } catch (e: HttpException) {
            Log.e("HTTP", e.message ?: "HttpException")
        }
    }
}