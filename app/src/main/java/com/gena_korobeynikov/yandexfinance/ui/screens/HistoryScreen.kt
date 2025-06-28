package com.gena_korobeynikov.yandexfinance.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.ui.states.UiState
import com.gena_korobeynikov.yandexfinance.ui.components.ListLoader
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem
import com.gena_korobeynikov.yandexfinance.ui.models.TransactionUi
import com.gena_korobeynikov.yandexfinance.ui.viewModels.HistoryViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun HistoryScreen(
    isIncomes: Boolean = false,
    viewModel: HistoryViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val startDate by viewModel.startDate.collectAsState()
    val endDate by viewModel.endDate.collectAsState()

    val accountId: Long = 1

    val context = LocalContext.current
    var showStartPicker by rememberSaveable { mutableStateOf(false) }
    var showEndPicker by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.setParams(accountId, isIncomes)
    }

    if (showStartPicker) {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                val newDate = LocalDate.of(year, month + 1, day)
                viewModel.updateStartDate(newDate)
                showStartPicker = false
            },
            startDate.year,
            startDate.monthValue - 1,
            startDate.dayOfMonth
        ).apply {
            setOnDismissListener { showStartPicker = false }
        }.show()
    }

    if (showEndPicker) {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                val newDate = LocalDate.of(year, month + 1, day)
                viewModel.updateEndDate(newDate)
                showEndPicker = false
            },
            endDate.year,
            endDate.monthValue - 1,
            endDate.dayOfMonth
        ).apply {
            setOnDismissListener { showEndPicker = false }
        }.show()

    }

        Column {
            MainListItem(
                mainText = stringResource(id = R.string.start),
                color = colorResource(id = R.color.secondary_green),
                huggingHeight = true,
                trailing = {
                    Text(
                        text = startDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = null,
                    )
                },
                modifier = Modifier.clickable { showStartPicker = true }
            )
            MainListItem(
                mainText = stringResource(id = R.string.end),
                color = colorResource(id = R.color.secondary_green),
                huggingHeight = true,
                trailing = {
                    Text(
                        text = endDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = null,
                    )
                },
                modifier = Modifier.clickable { showEndPicker = true }
            )


            ListLoader(uiState) {
                val transactions = (uiState as UiState.Success).data.list
                val totalSum = (uiState as UiState.Success).data.totalSum

                MainListItem(
                    mainText = stringResource(id = R.string.sum),
                    color = colorResource(id = R.color.secondary_green),
                    huggingHeight = true,
                    trailing = {
                        Text(
                            text = "$totalSum ${transactions.firstOrNull()?.currency ?: ""}",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                )
                HistoryList(transactions)
            }
        }

}


@Composable
fun HistoryList(transactions: List<TransactionUi>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {

        items(transactions, key = { it.id }) { expense ->
            MainListItem(
                emoji = expense.emoji,
                mainText = expense.categoryName,
                subtitle = expense.comment,
                trailing = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${expense.amount} ${expense.currency}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.on_surface),
                            )
                            Text(
                                text = expense.transactionDate,
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.on_surface),
                            )
                        }

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



