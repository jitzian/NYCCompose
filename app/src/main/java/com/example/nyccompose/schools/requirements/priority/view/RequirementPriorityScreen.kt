package com.example.nyccompose.schools.requirements.priority.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.nyccompose.R
import com.example.nyccompose.schools.detail.view.SchoolDetailScaffold
import com.example.nyccompose.schools.requirements.priority.domain.model.RequirementPriority
import com.example.nyccompose.schools.requirements.priority.viewmodel.RequirementPriorityScreenViewModel
import com.example.nyccompose.ui.app.NYCScreen
import com.example.nyccompose.ui.common.error.ConnectivityError
import com.example.nyccompose.ui.common.loading.LoadingScreen

@Composable
fun RequirementPriorityScreenState(
    onUpClick: () -> Unit,
    requirementPriorityScreenViewModel: RequirementPriorityScreenViewModel = viewModel()
) {
    val state by requirementPriorityScreenViewModel.state.collectAsState()

    when (state) {
        is RequirementPriorityScreenViewModel.UIState.Loading -> {
            LoadingScreen()
        }
        is RequirementPriorityScreenViewModel.UIState.Success -> {
            RequirementPriorityScreen(
                listOfRequirements = (state as RequirementPriorityScreenViewModel.UIState.Success).data,
                onUpClick = onUpClick
            )
        }
        is RequirementPriorityScreenViewModel.UIState.Error -> {
            ConnectivityError(message = (state as RequirementPriorityScreenViewModel.UIState.Error).message)
        }
    }
}

@Composable
fun RequirementPriorityScreen(
    listOfRequirements: List<RequirementPriority>,
    onUpClick: () -> Unit
) {

    NYCScreen {
        SchoolDetailScaffold(
            onUpClick = onUpClick,
            content = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    LazyColumn {
                        items(listOfRequirements) { item ->
                            RequirementPriorityItem(item)
                        }
                    }
                }
            },
            title = stringResource(id = R.string.schools_scores_TEXT)
        )
    }
}

@Composable
fun RequirementPriorityItem(data: RequirementPriority) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_16_dp),
                vertical = dimensionResource(id = R.dimen.dimen_8_dp)
            ),
        elevation = dimensionResource(id = R.dimen.dimen_4_dp)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.dimen_16_dp),
                vertical = dimensionResource(id = R.dimen.dimen_4_dp)
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "https://img.wallpapersafari.com/desktop/1600/900/22/68/JA7MEV.jpg"
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.dimen_8_dp))
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.dimen_8_dp)))
            )
            Text(
                text = data.schoolName.toString(),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = data.overViewParagraph.toString(),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(id = R.string.scores_TEXT),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.subtitle1
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.dimen_8_dp)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
            ) {
                Text(
                    text = stringResource(id = R.string.sat_test_takers_TEXT),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = data.numOfSatTestTakers.toString(),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.caption
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.dimen_8_dp)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
            ) {

                Text(
                    text = stringResource(id = R.string.reading_score_TEXT),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = data.satCriticalReadingAvgScore.toString(),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.caption
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.dimen_8_dp)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
            ) {

                Text(
                    text = stringResource(id = R.string.math_score_TEXT),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = data.satMathAvgScore.toString(),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.caption
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.dimen_8_dp),
                        bottom = dimensionResource(id = R.dimen.dimen_8_dp)
                    ),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
            ) {

                Text(
                    text = stringResource(id = R.string.writing_score_TEXT),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = data.satWritingAvgScore.toString(),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

