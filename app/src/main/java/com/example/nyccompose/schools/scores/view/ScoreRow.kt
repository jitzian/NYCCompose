package com.example.nyccompose.schools.scores.view

import androidx.compose.foundation.layout.Column
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
            .padding(dimensionResource(id = R.dimen.dimen_16_dp)),
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
                style = MaterialTheme.typography.h6
            )
            //TODO: Continue with the other 3 rows..
        }

    }
}