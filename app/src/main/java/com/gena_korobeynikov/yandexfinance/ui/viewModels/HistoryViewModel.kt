package com.gena_korobeynikov.yandexfinance.ui.viewModels

import com.gena_korobeynikov.yandexfinance.di.TemporaryServiceLocator
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.GetTransactionsForPeriodUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.totalAmount
import com.gena_korobeynikov.yandexfinance.ui.mappers.toMoneyFormat
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.TransactionListUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class HistoryViewModel(
    private val getTransactionsForPeriod : GetTransactionsForPeriodUseCase =
        GetTransactionsForPeriodUseCase(TemporaryServiceLocator.transactionsRepository),
) : BaseLoadViewModel<List<Transaction>, TransactionListUi>() {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private val _startDate = MutableStateFlow(LocalDate.now().withDayOfMonth(1))
    val startDate: StateFlow<LocalDate> = _startDate.asStateFlow()

    private val _endDate = MutableStateFlow(LocalDate.now())
    val endDate: StateFlow<LocalDate> = _endDate.asStateFlow()

    private var accountId: Long = 1L
    private var isIncome: Boolean = false

    init {
        loadTransactions()
    }

    fun setParams(accountId: Long, isIncome: Boolean) {
        this.accountId = accountId
        this.isIncome = isIncome
        loadTransactions()
    }

    fun updateStartDate(date: LocalDate) {
        _startDate.value = date
        loadTransactions()
    }

    fun updateEndDate(date: LocalDate) {
        _endDate.value = date
        loadTransactions()
    }


    private fun loadTransactions() {
        load(
            mapper = { list ->
                TransactionListUi(
                    list = list.map { it.toUi() },
                    totalSum = list.totalAmount().toMoneyFormat()
                )
            },
            block = { getTransactionsForPeriod(accountId, startDate.value.format(formatter), endDate.value.format(formatter), isIncome) }
        )
    }
}