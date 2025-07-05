package com.gena_korobeynikov.yandexfinance.ui.viewModels

import com.gena_korobeynikov.yandexfinance.di.TemporaryServiceLocator
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.GetTransactionsForPeriodUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.totalAmount
import com.gena_korobeynikov.yandexfinance.ui.mappers.toMoneyFormat
import com.gena_korobeynikov.yandexfinance.ui.mappers.toUi
import com.gena_korobeynikov.yandexfinance.ui.models.TransactionListUi
import java.time.LocalDate

/**Для начльного экрана расходов / доходов - за сегодняшний день**/
class TodayTransactionsViewModel(
    private val getTransactionsForPeriod : GetTransactionsForPeriodUseCase =
        GetTransactionsForPeriodUseCase(TemporaryServiceLocator.transactionsRepository),
) : BaseLoadViewModel<List<Transaction>, TransactionListUi>() {

    private val today = LocalDate.now().toString()

    fun loadTransactions(accountId : Long, isIncome : Boolean) {
        load(
            mapper = { list ->
                TransactionListUi(
                    list = list.map { it.toUi() },
                    totalSum = list.totalAmount().toMoneyFormat()
                )
            },
            block = { getTransactionsForPeriod(accountId, today, today, isIncome) }
        )
    }
}