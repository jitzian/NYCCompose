package com.example.nyccompose.ui.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nyccompose.ui.theme.NYCComposeTheme

@Composable
fun NYCApp(content: @Composable () -> Unit) {
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