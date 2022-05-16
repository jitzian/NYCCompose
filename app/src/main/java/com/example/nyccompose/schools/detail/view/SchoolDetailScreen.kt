package com.example.nyccompose.schools.detail.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.SchoolsResultItem
import com.example.nyccompose.schools.detail.viewmodel.SchoolDetailViewModel
import com.example.nyccompose.ui.app.NYCApp
import com.example.nyccompose.ui.common.error.ConnectivityError
import com.example.nyccompose.ui.common.loading.LoadingScreen

@Composable
fun SchoolDetailScreenState(
    onUpClick: () -> Unit,
    schoolViewModel: SchoolDetailViewModel = viewModel()
) {

    val state by schoolViewModel.state.collectAsState()

    when (state) {
        is SchoolDetailViewModel.UIState.Loading -> {
            LoadingScreen()
        }
        is SchoolDetailViewModel.UIState.Success -> {
            SchoolDetailScreen(
                school = (state as SchoolDetailViewModel.UIState.Success).school,
                onUpClick = onUpClick
            )
        }
        is SchoolDetailViewModel.UIState.Error -> {
            ConnectivityError(message = (state as SchoolDetailViewModel.UIState.Error).message)
        }
    }

}


@Composable
fun SchoolDetailScreen(
    school: SchoolsResultItem,
    onUpClick: () -> Unit
) {
    NYCApp {
        SchoolDetailScaffoldState(
            onUpClick = onUpClick
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.dimen_16_dp))
            ) {
                Text(
                    text = school.schoolName.toString(),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = school.overviewParagraph.toString(),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8_dp))
                )
                Divider(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.dimen_4_dp),
                        vertical = dimensionResource(id = R.dimen.dimen_8_dp)
                    )
                )
                Text(
                    text = school.academicopportunities1.toString(),
                    modifier = Modifier.padding(
                        vertical = dimensionResource(
                            id = R.dimen.dimen_8_dp
                        )
                    )
                )
                Text(
                    text = school.location.toString(),
                    modifier = Modifier.padding(
                        vertical = dimensionResource(
                            id = R.dimen.dimen_8_dp
                        )
                    ),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}