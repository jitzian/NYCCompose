package com.example.nyccompose.ui.common

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.nyccompose.ui.app.NYCApp

@Composable
fun MainAppBar() {
    TopAppBar(
        title = {
            Text(text = "This is a title")
        }
    )
}