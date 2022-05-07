package com.example.nyccompose.ui.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun MainAppBar(
    title: String,
    showBackButton: Boolean = false,
    onOpClick: (() -> Unit)? = null
) {
    if (showBackButton) {
        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                ArrowBackIcon(onOpClick = onOpClick)
            }
        )
    } else {
        TopAppBar(
            title = {
                Text(text = title)
            }
        )
    }
}