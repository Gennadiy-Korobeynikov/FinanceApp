package com.gena_korobeynikov.yandexfinance.data.mappers.toDto

import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.data.local.entities.TransactionWithRelations
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun TransactionWithRelations.toReadDto(): TransactionReadDto {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val transactionDateStr = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(transaction.transactionDate),
        ZoneId.systemDefault()
    ).format(formatter)

    val updatedAtStr = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(transaction.updatedAt),
        ZoneId.systemDefault()
    ).format(formatter)

    return TransactionReadDto(
        id = transaction.id,
        account = account.toDto(),
        category = category.toDto(),
        amount = transaction.amount.toString(),
        transactionDate = transactionDateStr,
        comment = transaction.comment,
        createdAt = updatedAtStr,
        updatedAt = updatedAtStr
    )
}

fun Transaction.toWriteDto(): TransactionWriteDto {


    return TransactionWriteDto(
        accountId = account.id,
        categoryId = category.id,
        amount = amount.toString(),
        transactionDate = transactionDate.atZone(ZoneId.of("UTC"))
            .format(DateTimeFormatter.ISO_INSTANT),
        comment = comment
    )
}