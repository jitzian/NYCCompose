package com.example.nyccompose.schools.main.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.SchoolsResultItem
import com.example.nyccompose.schools.main.viewmodel.MainViewModel
import com.example.nyccompose.ui.app.NYCApp
import com.example.nyccompose.ui.common.MainAppBar
import com.example.nyccompose.ui.common.error.ConnectivityError

@Composable
fun MainSchoolState(
    mainViewModel: MainViewModel = viewModel()
) {
    val data by mainViewModel.data.collectAsState()
    mainViewModel.fetchData()

    when (data) {
        is MainViewModel.UIState.Empty -> {
            ConnectivityError(message = stringResource(id = R.string.no_data_available_TEXT))
        }
        is MainViewModel.UIState.Success -> {
            MainSchools(schools = (data as MainViewModel.UIState.Success).listOfSchools)
        }
        is MainViewModel.UIState.Error -> {
            ConnectivityError(message = stringResource(id = R.string.an_error_occurred_text))
        }
    }
}

@Composable
fun MainSchools(schools: List<SchoolsResultItem>) {
    NYCApp {
        Scaffold(
            topBar = {
                MainAppBar()
            }
        ) {
            LazyColumn {
                items(schools) { school ->
                    ItemRow(data = school)
                }
            }
        }
    }
}