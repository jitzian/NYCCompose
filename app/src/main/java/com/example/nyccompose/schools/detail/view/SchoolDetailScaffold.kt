package com.example.nyccompose.schools.detail.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.nyccompose.ui.common.MainAppBar

@Composable
fun SchoolDetailScaffold(
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            MainAppBar(
                title = "Detail of School",
                showBackButton = true
            )
        },
        content = content
    )
}