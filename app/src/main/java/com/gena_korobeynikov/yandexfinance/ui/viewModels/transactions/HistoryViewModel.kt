package com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions

import com.gena_korobeynikov.yandexfinance.data.api.ACCOUNT_ID
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.GetTransactionsForPeriodUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.totalAmount
import com.gena_korobeynikov.yandexfinance.ui.mappers.toMoneyFormat
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.TransactionListUi
import com.gena_korobeynikov.yandexfinance.ui.viewModels.BaseLoadViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getTransactionsForPeriod : GetTransactionsForPeriodUseCase
) : BaseLoadViewModel<List<Transaction>, TransactionListUi>() {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private val _startDate = MutableStateFlow(LocalDate.now().withDayOfMonth(1))
    val startDate: StateFlow<LocalDate> = _startDate.asStateFlow()

    private val _endDate = MutableStateFlow(LocalDate.now())
    val endDate: StateFlow<LocalDate> = _endDate.asStateFlow()

    private var accountId: Long = ACCOUNT_ID
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