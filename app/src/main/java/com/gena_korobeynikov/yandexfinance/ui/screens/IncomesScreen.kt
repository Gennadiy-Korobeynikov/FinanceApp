package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.common.NetworkModule
import com.gena_korobeynikov.yandexfinance.data.toSymbol
import com.gena_korobeynikov.yandexfinance.domain.Transaction
import com.gena_korobeynikov.yandexfinance.domain.TransactionsRepositoryImpl
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem
import com.gena_korobeynikov.yandexfinance.ui.states.TransactionUiState
import com.gena_korobeynikov.yandexfinance.ui.viewModels.TransactionsViewModel
import java.time.LocalDate
import java.time.ZoneId

const val TOTAL_INCOMES = "600 000 ₽" // Пока здесь, потом с бэка

@Composable
    fun IncomesScreen(
        accountId: Long = 1, // Стоит по умолчанию для корректного вывода (для проверяющих), можно поменять
    ) {
    val viewModel = remember {
        TransactionsViewModel(
            repository = TransactionsRepositoryImpl(api = NetworkModule.transactionsApi)
        )
    }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(accountId) {
        viewModel.loadTransactions(accountId)
    }

    when (uiState) {
        is TransactionUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is TransactionUiState.Success -> {
            val transactions = (uiState as TransactionUiState.Success).transactions
            IncomesList(transactions)
        }

        is TransactionUiState.Error -> {
            val message = (uiState as TransactionUiState.Error).message
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Ошибка: $message", color = Color.Red)
            }
        }

    }
}



@Composable
fun IncomesList(transactions: List<Transaction>) {
    val zone = ZoneId.systemDefault()
    val incomesToday = transactions.filter { it.category.isIncome } // Только доходы
        .filter { it.transactionDate.atZone(zone).toLocalDate() == LocalDate.now(zone) } // Сегодня
        .sortedByDescending { it.transactionDate }// Сначала новые

    Column {
        MainListItem(
            mainText = "Всего",
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            trailing = {
                Text(
                    text = "${incomesToday.sumOf { it.amount }} ${incomesToday.firstOrNull()?.account?.currency?.toSymbol()}",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        )


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                incomesToday,
                key = { it.id }) { expense ->
                MainListItem(
                    emoji = expense.category.emoji,
                    mainText = expense.category.name,
                    subtitle = expense.comment,
                    trailing = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "${expense.amount} ${expense.account.currency.toSymbol()}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.on_surface),
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_more_vert),
                                contentDescription = null,
                            )
                        }
                    }
                )


            }
        }
    }
}
