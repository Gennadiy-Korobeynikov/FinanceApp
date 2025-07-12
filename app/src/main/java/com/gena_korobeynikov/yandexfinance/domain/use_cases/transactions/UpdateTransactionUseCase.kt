package com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.di.scopes.TransactionsScope
import com.gena_korobeynikov.yandexfinance.domain.repos.TransactionsRepository
import javax.inject.Inject

@TransactionsScope
class UpdateTransactionUseCase @Inject constructor(
    private val repository: TransactionsRepository,
) {
    suspend operator fun invoke(
        transactionId : Long,
        transaction: TransactionWriteDto
    ) : TransactionReadDto
    {
        return repository.updateTransaction(transactionId,transaction)
    }
}

