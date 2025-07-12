package com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gena_korobeynikov.yandexfinance.data.api.ACCOUNT_ID
import com.gena_korobeynikov.yandexfinance.data.dto.AccountDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionReadDto
import com.gena_korobeynikov.yandexfinance.data.dto.TransactionWriteDto
import com.gena_korobeynikov.yandexfinance.di.scopes.TransactionsScope
import com.gena_korobeynikov.yandexfinance.domain.models.Transaction
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.GetTransactionByIdUseCase
import com.gena_korobeynikov.yandexfinance.domain.use_cases.transactions.UpdateTransactionUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class EditTransactionViewModel @AssistedInject constructor(
    @Assisted private val transactionId: Long,
    private val updateTransactionUC: UpdateTransactionUseCase,
    private val getTransactionUC: GetTransactionByIdUseCase
) : ViewModel() {

    var transaction by mutableStateOf<TransactionReadDto?>(null)
        private set

    init {
        viewModelScope.launch {
            transaction = getTransactionUC(transactionId)
        }
    }

    fun updateTransaction(newCategoryId: Int, newAmount: String, newDate: String, newComment : String) {
        val editedTransaction = TransactionWriteDto(
            accountId = ACCOUNT_ID,
            categoryId = newCategoryId,
            amount = newAmount.replace(',', '.'),
            transactionDate = newDate,
            comment = newComment
        )
        viewModelScope.launch {
            updateTransactionUC(transaction!!.id, editedTransaction)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(accountId: Long): EditTransactionViewModel
    }
}