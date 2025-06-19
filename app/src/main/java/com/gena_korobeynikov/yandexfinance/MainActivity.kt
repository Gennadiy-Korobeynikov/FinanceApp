package com.gena_korobeynikov.yandexfinance

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
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
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gena_korobeynikov.yandexfinance.ui.navigation.Screen
import com.gena_korobeynikov.yandexfinance.ui.navigation.NavigationGraph
import com.gena_korobeynikov.yandexfinance.ui.theme.YandexFinanceTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Log.i("a", Screen.all.toString())

        setContent {
            YandexFinanceTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
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
                        val currentScreen = Screen.all.find { it.route == currentRoute }

                        CenterAlignedTopAppBar(
                            windowInsets = TopAppBarDefaults.windowInsets,
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = colorResource(id= R.color.primary_green),
                                titleContentColor = colorResource(id = R.color.on_surface),
                                actionIconContentColor = colorResource(id = R.color.on_surface_variant)
                            ),
                            title = {
                                    Text(
                                        text = stringResource(
                                            id = currentScreen?.titleRes ?: R.string.app_name
                                        ),
                                        style = MaterialTheme.typography.titleLarge,
                                        color = colorResource(id = R.color.on_surface)
                                    )
                            },
                            actions = {
                                when (currentScreen?.route) {
                                    Screen.Expenses.route -> IconButton(onClick = { /* История расходов */ }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_history),
                                            contentDescription = "История расходов"
                                        )
                                    }

                                    Screen.Incomes.route  -> IconButton(onClick = { /* История доходов */ }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_history),
                                            contentDescription = "История доходов"
                                        )
                                    }

                                    Screen.Account.route  -> IconButton(onClick = { /* Реадктировать счёт */ }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_edit),
                                            contentDescription = "Реадктировать счёт "
                                        )
                                    }

                                    else -> {}
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
                                Screen.all.forEach { item ->
                                    val isSelected = currentRoute == item.route
                                    NavigationBarItem(
                                        selected = isSelected,
                                        onClick = {
                                            navController.safeNavigate(item)
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
                                                painter = painterResource(id = item.iconRes),
                                                contentDescription = null,
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = stringResource(id = item.navBarItemTitleRes),
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
    }


    fun NavHostController.safeNavigate(screen: Screen) {
        if (currentDestination?.route != screen.route) {
            navigate(screen.route) {
                popUpTo(graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}