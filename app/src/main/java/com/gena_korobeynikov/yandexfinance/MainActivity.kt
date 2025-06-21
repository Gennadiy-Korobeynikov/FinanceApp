package com.gena_korobeynikov.yandexfinance

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
                                containerColor = colorResource(id= R.color.primary_green),
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
                            actions = {
                                if (currentScreen.topBarBtnIconRes != null) {
                                    IconButton(onClick = { currentScreen.topBarBtnAction?.invoke(navController) }) {
                                        Icon(
                                            painter = painterResource(id = currentScreen.topBarBtnIconRes),
                                            contentDescription = getString( currentScreen.titleRes)
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
    }


    fun NavHostController.safeNavigate(root: Screen) {
        val currentRoot = Screen.rootOf(currentBackStackEntry?.destination?.route)

        // Если уже на этом root-графе — очищаем вложенную навигацию до начального экрана
        if (currentRoot == root) {
            val startChild = Screen.all.find { it.root == root && it != root }
            startChild?.let {
                navigate(it.route) {
                    popUpTo(root.route) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            }
        } else {
            // Переход к root-графу
            navigate(root.route) {
                popUpTo(Screen.ExpensesRoot.route) {  // здесь можно использовать root.route
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}