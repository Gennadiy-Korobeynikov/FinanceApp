package com.gena_korobeynikov.yandexfinance.data.mappers.toDomain

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.local.entities.TransactionWithRelations
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import java.time.Instant

fun TransactionReadDto.toDomain(): Transaction {
    return Transaction(
        id = id,
        account = account.toDomain(),
        category = category.toDomain(),
        amount = amount.toBigDecimal(),
        transactionDate = Instant.parse(transactionDate),
        comment = comment,
        createdAt =  Instant.parse(createdAt),
        updatedAt = Instant.parse(updatedAt)
    )
}

fun TransactionWithRelations.toDomain(): Transaction {
    return Transaction(
        id = transaction.id,
        account = account.toDomain(),
        category = category.toDomain(),
        amount = transaction.amount.toBigDecimal(),
        transactionDate = Instant.ofEpochMilli(transaction.transactionDate),
        comment = transaction.comment,
        createdAt = Instant.ofEpochMilli(transaction.createdAt),
        updatedAt = Instant.ofEpochMilli(transaction.updatedAt)
    )
}