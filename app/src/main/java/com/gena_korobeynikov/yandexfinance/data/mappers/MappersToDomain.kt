package com.gena_korobeynikov.yandexfinance.data.mappers

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.dto.CategoryDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.domain.models.Account
import com.gena_korobeynikov.yandexfinance.domain.models.Category
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
        createdAt = Instant.parse(createdAt),
        updatedAt = Instant.parse(updatedAt)
    )
}

fun AccountDto.toDomain(): Account {
    return Account(
        id = id,
        name = name,
        balance = balance.toBigDecimal(),
        currency = currency
    )
}

fun CategoryDto.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
}