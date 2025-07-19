package com.gena_korobeynikov.yandexfinance.data.mappers.toDomain

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.local.entities.AccountEntity
import com.gena_korobeynikov.yandexfinance.domain.models.Account

fun AccountDto.toDomain(): Account {
    return Account(
        id = id,
        name = name,
        balance = balance.toBigDecimal(),
        currency = currency
    )
}


fun AccountEntity.toDomain(): Account {
    return Account(
        id = id,
        name = name,
        balance = balance.toBigDecimal(),
        currency = currency
    )
}

