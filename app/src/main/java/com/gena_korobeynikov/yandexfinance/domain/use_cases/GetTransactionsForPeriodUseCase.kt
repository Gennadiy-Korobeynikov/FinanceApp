package com.gena_korobeynikov.yandexfinance.domain.use_cases

import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.repos.TransactionsRepository
import java.math.BigDecimal
import java.time.LocalDate

class GetTransactionsForPeriodUseCase(
    private val repository: TransactionsRepository,
) {
    suspend operator fun invoke(
        accountId: Long, startDate: String?, endDate: String?, isIncomes: Boolean
    ) : List<Transaction>
    {
        return repository.getTransactionsForPeriod(accountId,startDate,endDate).filter { it.category.isIncome == isIncomes} // Расходы/доходы
            .sortedByDescending { it.transactionDate }// Сначала новые
    }
}

fun List<Transaction>.totalAmount(): BigDecimal {
    return sumOf { it.amount }
}