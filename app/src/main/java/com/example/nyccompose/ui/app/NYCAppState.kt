package com.example.nyccompose.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nyccompose.ui.navigation.NavItem
import com.example.nyccompose.utils.navigatePoppingUpToStartDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): NYCAppState =
    remember(navController) {
        NYCAppState(navController, coroutineScope)
    }

class NYCAppState(val navController: NavHostController, private val coroutineScope: CoroutineScope) {
    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    fun onNavItemClick(navItem: NavItem) {
        coroutineScope.launch {
            navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
        }
    }
}
