@file:OptIn(ExperimentalMaterial3Api::class)

package com.gena_korobeynikov.yandexfinance.ui.screens.categories

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.data.api.ACCOUNT_ID
import com.gena_korobeynikov.yandexfinance.ui.states.UiState
import com.gena_korobeynikov.yandexfinance.ui.components.ListLoader
import com.gena_korobeynikov.yandexfinance.ui.components.MainListItem
import com.gena_korobeynikov.yandexfinance.ui.models.CategoryUi
import com.gena_korobeynikov.yandexfinance.ui.viewModels.categories.CategoriesViewModel
import com.gena_korobeynikov.yandexfinance.ui.viewModels_factories.LocalCategoriesViewModelFactory


@Composable
    fun CategoriesScreen(
    accountId : Long = ACCOUNT_ID, // Стоит по умолчанию для корректного вывода (для проверяющих), можно поменять
    ) {
    val factory = LocalCategoriesViewModelFactory.current
    val viewModel: CategoriesViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(accountId) {
        viewModel.loadCategories()
    }


    ListLoader(
        uiState
    ) {
        val categories = (uiState as UiState.Success).data
        var searchText by remember { mutableStateOf("") }

        val filteredCategories = remember(searchText, categories) {
            if (searchText.isBlank()) categories
            else categories.filter {
                it.name.contains(searchText, ignoreCase = true) // TODO
            }
        }


        Column {
            SearchField(
                text = searchText,
                onTextChange = { searchText = it }
            )
            CategoryList(filteredCategories)
        }
    }

}




@Composable
fun CategoryList(categories: List<CategoryUi>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(categories, key = { it.id }) { category ->
            MainListItem(
                emoji = category.emoji,
                mainText = category.name,
                trailing = {}
            )
        }
    }
}

@Composable
fun SearchField(
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),

        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(
            text = stringResource(R.string.find_category),
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(id=R.color.on_surface_variant)) },

        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(R.string.find_category),
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
//
//@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
//@Composable
//fun SearchFieldPreview() {
//    MaterialTheme {
//        SearchField()
//    }
//}
