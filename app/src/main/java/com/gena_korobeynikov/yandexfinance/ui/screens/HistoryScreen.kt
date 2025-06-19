package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.models.Expenses
import com.gena_korobeynikov.yandexfinance.models.expenses
import com.gena_korobeynikov.yandexfinance.models.his_test_expenses
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem


@Composable
fun HistoryScreen() {
    Column {
        MainListItem(
            mainText = stringResource(id = R.string.start),
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            trailing = {
                Text(
                    text = "Февраль 2025" ,//TODO
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        )
        MainListItem(
            mainText = stringResource(id = R.string.end),
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            trailing = {
                Text(
                    text = "23:41" ,//TODO
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        )
        MainListItem(
            mainText = stringResource(id = R.string.sum),
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            trailing = {
                Text(
                    text = "125 868 P" ,//TODO
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        )

        ExpensesHistoryList(his_test_expenses)
    }
}


@Composable
fun ExpensesHistoryList(expenses: List<Expenses>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(expenses, key = { it.id }) { expense ->
            MainListItem(
                emoji = expense.emoji,
                mainText = expense.title,
                subtitle = expense.subtitle,
                trailing = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column (
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${expense.value} ₽",
                                style = MaterialTheme.typography.bodyLarge,
                                color = colorResource(id = R.color.on_surface),
                            )
                            Text(
                                text = "${expense.his_time}",
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


