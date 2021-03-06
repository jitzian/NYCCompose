package com.example.nyccompose.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.nyccompose.R

@Composable
fun ArrowBackIcon(onUpClick: () -> Unit) {
//fun ArrowBackIcon() {
    IconButton(
        onClick = onUpClick
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.back_TEXT)
        )
    }
}