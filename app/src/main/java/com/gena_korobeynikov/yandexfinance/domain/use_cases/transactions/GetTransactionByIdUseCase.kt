package com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.di.scopes.TransactionsScope
import com.gena_korobeynikov.yandexfinance.domain.repos.TransactionsRepository
import javax.inject.Inject

@TransactionsScope
class GetTransactionByIdUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {
    suspend operator fun invoke(
        transactionId: Long,
    ): TransactionReadDto {
        return transactionsRepository.getTransactionById(transactionId)
    }
}