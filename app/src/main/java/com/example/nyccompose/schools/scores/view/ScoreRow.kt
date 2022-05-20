package com.example.nyccompose.schools.scores.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.ScoresResultItem

@Composable
fun ScoreRow(data: ScoresResultItem) {
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
            )
        ) {
            Text(
                text = data.schoolName ?: stringResource(id = R.string.no_data_available_TEXT),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.dimen_8_dp),
                    bottom = dimensionResource(id = R.dimen.dimen_8_dp),
                )
            )
            DetailScoreRow(
                leftColumnContent = stringResource(id = R.string.sat_test_takers_TEXT),
                rightColumnContent = data.numOfSatTestTakers
                    ?: stringResource(id = R.string.no_data_available_TEXT),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_8_dp))
            )

            DetailScoreRow(
                leftColumnContent = stringResource(id = R.string.reading_score_TEXT),
                rightColumnContent = data.readingScore
                    ?: stringResource(id = R.string.no_data_available_TEXT),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_8_dp))
            )

            DetailScoreRow(
                leftColumnContent = stringResource(id = R.string.math_score_TEXT),
                rightColumnContent = data.mathScore
                    ?: stringResource(id = R.string.no_data_available_TEXT),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_8_dp))
            )

            DetailScoreRow(
                leftColumnContent = stringResource(id = R.string.writing_score_TEXT),
                rightColumnContent = data.writingScore
                    ?: stringResource(id = R.string.no_data_available_TEXT),
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.dimen_8_dp),
                    bottom = dimensionResource(id = R.dimen.dimen_8_dp)
                )
            )
        }
    }
}

@Composable
fun DetailScoreRow(
    leftColumnContent: String,
    rightColumnContent: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = leftColumnContent,
            modifier = Modifier.weight(.5f, fill = true),
            style = MaterialTheme.typography.caption
        )
        Text(
            text = rightColumnContent,
            modifier = Modifier.weight(.5f, fill = true),
            style = MaterialTheme.typography.caption
        )
    }
}