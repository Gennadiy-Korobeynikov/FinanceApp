package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.common.NetworkModule
import com.gena_korobeynikov.yandexfinance.data.toSymbol
import com.gena_korobeynikov.yandexfinance.domain.Account
import com.gena_korobeynikov.yandexfinance.domain.AccountRepositoryImpl
import com.gena_korobeynikov.yandexfinance.domain.TransactionsRepositoryImpl
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem
import com.gena_korobeynikov.yandexfinance.ui.states.AccountUiState
import com.gena_korobeynikov.yandexfinance.ui.states.TransactionUiState
import com.gena_korobeynikov.yandexfinance.ui.viewModels.AccountViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels.TransactionsViewModel


@Composable
    fun AccountScreen(
        accountId: Long = 1, // Стоит по умолчанию для корректного вывода (для проверяющих), можно поменять
    ) {
    val viewModel = remember {
        AccountViewModel(
            repository = AccountRepositoryImpl(api = NetworkModule.accountApi)
        )
    }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(accountId) {
        viewModel.loadAccount(accountId)
    }

    when (uiState) {
        is AccountUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is AccountUiState.Success -> {
            val account = (uiState as AccountUiState.Success).account
            AccountInfo(account)
        }

        is AccountUiState.Error -> {
            val message = (uiState as AccountUiState.Error).message
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
fun AccountInfo(
    account: Account
) {
    Column {
        MainListItem(
            mainText = "Баланс",
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            emoji = "💰",
            emojiWhiteBg = true,
            trailing = {
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "${account.balance} ${account.currency.toSymbol()}",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = null,
                    )
                }
            }
        )

        MainListItem(
            mainText = "Валюта",
            color = colorResource(id = R.color.secondary_green),
            huggingHeight = true,
            trailing = {
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = account.currency.toSymbol(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = null,
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Image(
            modifier = Modifier.fillMaxWidth().height(233.dp),
            painter = painterResource(id = R.drawable.mock_diagram),
            contentDescription = "Диаграмма",
        )

    }
}

