package com.gena_korobeynikov.yandexfinance.data

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.dto.CategoryDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionDto
import com.gena_korobeynikov.yandexfinance.domain.Account
import com.gena_korobeynikov.yandexfinance.domain.Category
import com.gena_korobeynikov.yandexfinance.domain.Transaction

import java.time.Instant

fun TransactionDto.toDomain(): Transaction {
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