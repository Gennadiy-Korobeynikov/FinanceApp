package com.gena_korobeynikov.yandexfinance.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gena_korobeynikov.yandexfinance.R
import com.gena_korobeynikov.yandexfinance.ui.navigation.NavigationGraph
import com.gena_korobeynikov.yandexfinance.ui.navigation.Screen
import com.gena_korobeynikov.yandexfinance.ui.navigation.safeNavigate
import com.gena_korobeynikov.yandexfinance.ui.theme.YandexFinanceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    YandexFinanceTheme {
        val navController = rememberNavController()
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        val currentRoot = Screen.rootOf(currentRoute)
        val fabAction = Screen.fromRoute(currentRoute)?.addBtnAction


        Scaffold(
            floatingActionButton = {
                if (fabAction != null) {
                    FloatingActionButton(
                        onClick = fabAction,
                        shape = CircleShape,
                        containerColor = colorResource(id = R.color.primary_green),
                        contentColor = Color.White,
                        elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "Добавить",

                            )
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End,


            topBar = {
                val currentScreen = Screen.all.find { it.route == currentRoute } ?: Screen.Expenses

                CenterAlignedTopAppBar(
                    windowInsets = TopAppBarDefaults.windowInsets,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = colorResource(id = R.color.primary_green),
                        titleContentColor = colorResource(id = R.color.on_surface),
                        actionIconContentColor = colorResource(id = R.color.on_surface_variant)
                    ),
                    title = {
                        Text(
                            text = stringResource(
                                id = currentScreen.titleRes
                            ),
                            style = MaterialTheme.typography.titleLarge,
                            color = colorResource(id = R.color.on_surface)
                        )
                    },
                    navigationIcon = {
                        if (currentScreen !in Screen.allNavBar) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_back),
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                    actions = {
                        if (currentScreen.topBarBtnIconRes != null) {
                            IconButton(onClick = {
                                currentScreen.topBarBtnAction?.invoke(
                                    navController
                                )
                            }) {
                                Icon(
                                    painter = painterResource(id = currentScreen.topBarBtnIconRes),
                                    contentDescription = stringResource(currentScreen.titleRes)
                                )
                            }
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = colorResource(id = R.color.surface_container),

                    ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Screen.allNavBar.forEach { item ->
                            val isSelected = currentRoot == item.root
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = {
                                    navController.safeNavigate(item.root)
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = colorResource(id = R.color.primary_green),
                                    unselectedIconColor = colorResource(id = R.color.on_surface_variant),
                                    selectedTextColor = colorResource(id = R.color.on_surface),
                                    unselectedTextColor = colorResource(id = R.color.on_surface_variant),
                                    indicatorColor = colorResource(id = R.color.secondary_green),
                                ),
                                icon = {
                                    Icon(
                                        painter = painterResource(id = item.navBarIconRes!!),
                                        contentDescription = null,
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(id = item.navBarItemTitleRes!!),
                                        style = MaterialTheme.typography.labelMedium,
                                    )
                                }
                            )
                        }
                    }
                }
            },

            ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavigationGraph(navController = navController)
            }
        }
    }
}