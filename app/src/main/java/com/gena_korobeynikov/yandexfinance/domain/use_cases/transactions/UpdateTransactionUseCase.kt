package com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.di.scopes.TransactionsScope
import com.gena_korobeynikov.yandexfinance.domain.repos.transactions.TransactionsRepository
import javax.inject.Inject

@TransactionsScope
class UpdateTransactionUseCase @Inject constructor(
    private val repository: TransactionsRepository,
) {
    suspend operator fun invoke(
        transactionId : Long,
        transaction: TransactionWriteDto
    )
    {
        return repository.updateTransaction(transactionId,transaction)
    }
}

