package com.gena_korobeynikov.yandexfinance.data.mappers.toEntity

import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.local.entities.AccountEntity
import com.gena_korobeynikov.yandexfinance.domain.models.Account

fun AccountDto.toEntity(): AccountEntity {
    return AccountEntity(
        id = id,
        name = name,
        balance = balance.toDouble(),
        currency = currency,
    )
}