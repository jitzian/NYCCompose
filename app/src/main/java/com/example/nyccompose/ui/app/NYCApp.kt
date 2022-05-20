package com.example.nyccompose.ui.app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.nyccompose.ui.navigation.AppBottomNavigation
import com.example.nyccompose.ui.navigation.NavigationConfig
import com.example.nyccompose.ui.theme.NYCComposeTheme

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NYCApp() {
    val appState = rememberAppState()

    NYCScreen {
        Scaffold(
            bottomBar = {
                AppBottomNavigation(currentRoute = appState.currentRoute, onNavItemClick = {

                    appState.onNavItemClick(it)
                })
            }) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavigationConfig(appState.navController)
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