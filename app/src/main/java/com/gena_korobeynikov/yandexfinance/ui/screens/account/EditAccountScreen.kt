package com.gena_korobeynikov.yandexfinance.ui.screens.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.ui.components.CurrencyBottomSheetContent
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem
import com.gena_korobeynikov.yandexfinance.ui.viewModels.account.EditAccountViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.EditAccountViewModelFactory
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalEditAccountVMFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountScreen(
    accountId: Long,
    navController: NavHostController,
) {
    val assistedFactory = LocalEditAccountVMFactory.current
    val viewModelFactory = remember(accountId) {
        EditAccountViewModelFactory(assistedFactory, accountId)
    }

    val viewModel: EditAccountViewModel = viewModel(factory = viewModelFactory)

    val account = viewModel.account
    if (account != null) {
        var name by rememberSaveable { mutableStateOf(account.name) }
        var balance by rememberSaveable { mutableStateOf(account.balance.replace(" ", "")) }
        var currency by rememberSaveable { mutableStateOf(account.currency) }
        var isSheetOpen by rememberSaveable { mutableStateOf(false) }

        Column(
        ) {
            MainListItem(
                emoji = "💰",
                mainText = "Название счёта",
                huggingHeight = true,
                trailing = {
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .padding(end = 8.dp)
                    ) {
                        BasicTextField(
                            value = name,
                            onValueChange = { name = it },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.End
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            )

            MainListItem(
                mainText = "Баланс",
                huggingHeight = true,
                trailing = {
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .padding(end = 8.dp)
                    ) {
                        BasicTextField(
                            value = balance,
                            onValueChange = { input: String ->
                                val filtered = input.filterIndexed { index, c ->
                                    c.isDigit() || c == ',' || (c == '-' && index == 0)
                                }
                                val normalized = filtered.replace('.', ',')
                                val regex = Regex("""^-?\d*(,\d{0,2})?$""")
                                if (normalized.isEmpty() || regex.matches(normalized)) {
                                    balance = normalized
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
                mainText = "Валюта",
                huggingHeight = true,

                trailing = {
                    Text(
                        text = currency,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                modifier = Modifier.clickable { isSheetOpen = true }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.updateAccount(name, balance, currency)
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_green)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.6f)
            ) {
                Text(
                    text = "Сохранить",
                    color = colorResource(id = R.color.on_surface_variant)
                )
            }
        }

        if (isSheetOpen) {
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ModalBottomSheet(
                onDismissRequest = { isSheetOpen = false },
                sheetState = sheetState
            ) {
                CurrencyBottomSheetContent(
                    onCurrencySelected = { selectedCurrencyCode ->
                        currency = selectedCurrencyCode
                        isSheetOpen = false
                    },
                    onCancel = { isSheetOpen = false }
                )
            }
        }
    }
}