package com.example.nyccompose.ui.common.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.nyccompose.R
import com.example.nyccompose.ui.app.NYCScreen

@Composable
fun ConnectivityError(message: String?) {
    NYCScreen {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.transpatent_owl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.cell_min_width))
                    .height(dimensionResource(id = R.dimen.cell_min_width))
                    .padding(dimensionResource(id = R.dimen.dimen_16_dp))

            )
            Text(
                text = message ?: stringResource(id = R.string.no_internet_connection_TEXT),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_16_dp)),
                style = MaterialTheme.typography.h5
            )
        }
    }
}