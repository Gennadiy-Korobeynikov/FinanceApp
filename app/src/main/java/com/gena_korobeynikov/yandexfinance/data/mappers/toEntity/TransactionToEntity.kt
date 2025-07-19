package com.gena_korobeynikov.yandexfinance.data.mappers.toEntity

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.data.local.entities.TransactionEntity
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import java.time.Instant


fun TransactionWriteDto.toEntity(id : Long): TransactionEntity {
    return TransactionEntity(
        id = id,
        accountId = accountId,
        categoryId = categoryId,
        amount = amount.toDouble(),
        transactionDate = Instant.parse(transactionDate).toEpochMilli(),
        comment = comment,
        createdAt = Instant.now().toEpochMilli(),
        updatedAt = Instant.now().toEpochMilli()
    )
}

fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        accountId = account.id,
        categoryId = category.id,
        amount = amount.toDouble(),
        transactionDate = transactionDate.toEpochMilli(),
        comment = comment,
        createdAt = Instant.now().toEpochMilli(),
        updatedAt = Instant.now().toEpochMilli()
    )
}