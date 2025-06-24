package com.gena_korobeynikov.yandexfinance.ui.viewModels

import com.gena_korobeynikov.yandexfinance.domain.Transaction
import com.gena_korobeynikov.yandexfinance.domain.TransactionsRepository


class TransactionsViewModel(
    private val repository: TransactionsRepository
) : BaseLoadViewModel<List<Transaction>>() {

    fun loadTransactions(accountId: Long, startDate: String? = null, endDate: String? = null) {
        load {
            repository.getTransactionsForPeriod(accountId, startDate, endDate)
        }
    }
}