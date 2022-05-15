package com.example.nyccompose.schools.scores.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.ScoresResultItem
import com.example.nyccompose.schools.detail.view.SchoolDetailScaffold
import com.example.nyccompose.schools.scores.viewmodel.ScoresScreenViewModel
import com.example.nyccompose.ui.app.NYCApp
import com.example.nyccompose.ui.common.error.ConnectivityError

//TODO: Rename file

@Composable
fun ScoresScreenState(
    scoresVM: ScoresScreenViewModel = viewModel(),
    onUpClick: () -> Unit
) {

    val data by scoresVM.state.collectAsState()
    scoresVM.fetchScores()

    when (data) {
        is ScoresScreenViewModel.UIState.Loading -> {

        }
        is ScoresScreenViewModel.UIState.Success -> {
            ScoresScreen(
                scores = (data as ScoresScreenViewModel.UIState.Success).listOfScores,
                onUpClick = onUpClick
            )
        }
        is ScoresScreenViewModel.UIState.Error -> {
            ConnectivityError(message = stringResource(id = R.string.no_data_available_TEXT))
        }
    }
}

@Composable
fun ScoresScreen(
    scores: List<ScoresResultItem>,
    onUpClick: () -> Unit
) {
    NYCApp {
        SchoolDetailScaffold(
            onUpClick = onUpClick,
            content = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    LazyColumn {
                        items(scores) { item ->
                            ScoreRow(data = item)
                        }
                    }
                }
            },
            //TODO: Implement cache for getting the school details as well
            school = null,
            title = stringResource(id = R.string.details_of_scores_TEXT)
        )
    }
}