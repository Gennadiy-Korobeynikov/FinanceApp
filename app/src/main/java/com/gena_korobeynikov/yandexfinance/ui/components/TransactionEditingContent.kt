package com.gena_korobeynikov.yandexfinance.ui.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.ui.viewModels.transactions.EditTransactionViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.EditTransactionViewModelFactory
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalEditTransactionVMFactory
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime

@Composable
fun TransactionEditingContent(
    transactionId : Long,
    navController : NavController,
    isIncome : Boolean
) {
    val assistedFactory = LocalEditTransactionVMFactory.current
    val viewModelFactory = remember(transactionId) {
        EditTransactionViewModelFactory(assistedFactory, transactionId)
    }
    val viewModel: EditTransactionViewModel = viewModel(factory = viewModelFactory)

    val transaction = viewModel.transaction
    if (transaction != null) {
        val categories = viewModel.categories
        var amount by rememberSaveable { mutableStateOf(transaction.amount) }

        val offsetDateTime = OffsetDateTime.parse(transaction.transactionDate)
        var date by rememberSaveable { mutableStateOf(offsetDateTime.toLocalDate()) }
        var time by rememberSaveable { mutableStateOf(offsetDateTime.toLocalTime()) }
        var categoryId by rememberSaveable { mutableLongStateOf(transaction.category.id) }
        var comment by rememberSaveable { mutableStateOf(transaction.comment) }
        val selectedCategory = categories.find { it.id == categoryId }

        val context = LocalContext.current

        var categoriesMenuExpanded by rememberSaveable { mutableStateOf(false) }
        var showTimePicker by rememberSaveable { mutableStateOf(false) }
        var showDatePicker by rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            viewModel.loadCategories(isIncome)
        }

        Column {
//        MainListItem(
//            mainText = "Cчёт",
//            trailing = {
//                Text("AccountName")
//            }
//        )

            MainListItem(
                mainText = "Статья",
                trailing = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = selectedCategory?.let { "${it.emoji} ${it.name}" } ?: "Выберите",
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorResource(id = R.color.on_surface),
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more_vert),
                            contentDescription = null,
                        )
                        DropdownMenu(
                            expanded = categoriesMenuExpanded,
                            onDismissRequest = { categoriesMenuExpanded = false }
                        ) {
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text("${category.emoji} ${category.name}") },
                                    onClick = {
                                        categoryId = category.id
                                        categoriesMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                },
                modifier = Modifier.clickable { categoriesMenuExpanded = true }
            )

            MainListItem(
                mainText = "Сумма",
                trailing = {
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .padding(end = 8.dp)
                    ) {
                        BasicTextField(
                            value = amount,
                            onValueChange = { input: String ->
                                val filtered = input.filterIndexed { index, c ->
                                    c.isDigit() || c == ',' || (c == '-' && index == 0)
                                }
                                val normalized = filtered.replace('.', ',')
                                val regex = Regex("""^-?\d*(,\d{0,2})?$""")
                                if (normalized.isEmpty() || regex.matches(normalized)) {
                                    amount = normalized
                                }
                            },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.End
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            )

            MainListItem(
                mainText = "Дата",
                trailing = {
                    Text(
                        text = date.toString(),
                        modifier = Modifier
                            .clickable { showDatePicker = true }
                            .padding(end = 8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(id = R.color.on_surface)
                    )
                }
            )

            MainListItem(
                mainText = "Время",
                trailing = {
                    Text(
                        text = time.toString().substring(0, 5),
                        modifier = Modifier
                            .clickable { showTimePicker = true }
                            .padding(end = 8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(id = R.color.on_surface)
                    )
                }
            )


            MainListItem(
                mainText = "Комментарий",
                trailing = {
                    BasicTextField(
                        value = comment?: "",
                        onValueChange = { comment = it },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.End
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .width(160.dp)
                            .padding(end = 8.dp)
                    )
                }
            )

            if (showDatePicker) {
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        date = LocalDate.of(year, month + 1, day)
                        showDatePicker = false
                    },
                    date.year,
                    date.monthValue - 1,
                    date.dayOfMonth
                ).apply {
                    setOnDismissListener { showDatePicker = false }
                }.show()
            }

            if (showTimePicker) {
                val context = LocalContext.current
                TimePickerDialog(
                    context,
                    { _, hour, minute ->
                        time = LocalTime.of(hour, minute)
                        showTimePicker = false
                    },
                    time.hour,
                    time.minute,
                    true
                ).apply {
                    setOnDismissListener { showTimePicker = false }
                }.show()
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.updateTransaction(
                        categoryId = categoryId,
                        amount = amount,
                        date = date,
                        time = time,
                        comment = comment
                    )
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_green)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.6f)
            ) {
                Text(
                    text = "Добавить",
                    color = colorResource(id = R.color.on_surface_variant)
                )
            }
        }
    }
}


