@file:OptIn(ExperimentalMaterial3Api::class)

package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.models.Expenses
import com.gena_korobeynikov.yandexfinance.models.expenses
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem


@Composable
    fun CategoriesScreen() {
        Column {
            SearchField()
            CategoryList(expenses)
        }
    }



@Composable
fun CategoryList(expenses: List<Expenses>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(expenses, key = { it.id }) { expense ->
            MainListItem(
                emoji = expense.emoji,
                mainText = expense.title,
                trailing = {}
            )
        }
    }
}

@Composable
fun SearchField() {
    var text by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),

        value = text,
        onValueChange = { text = it },
        placeholder = { Text(
            text = "Найти статью",
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(id=R.color.on_surface_variant)) },

        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Найти статью",
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.surface_container_high),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

        ),
        shape = RectangleShape,

    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun SearchFieldPreview() {
    MaterialTheme {
        SearchField()
    }
}
