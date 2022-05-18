package com.example.nyccompose.ui.app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.nyccompose.ui.navigation.NavItem
import com.example.nyccompose.ui.navigation.NavigationConfig
import com.example.nyccompose.ui.theme.NYCComposeTheme

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NYCApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    NYCScreen {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    NavItem.values().forEach { item ->
                        val title = stringResource(item.title)
                        BottomNavigationItem(
                            selected = currentRoute.contains(item.navCommand.feature.route),
                            onClick = {
                                navController.navigate(item.navCommand.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(imageVector = item.icon, contentDescription = title)
                            },
                            label = {
                                Text(text = title)
                            }
                        )
                    }
                }
            }) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavigationConfig(navController)
            }
        }
    }
}

@Composable
fun NYCScreen(content: @Composable () -> Unit) {
    NYCComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}