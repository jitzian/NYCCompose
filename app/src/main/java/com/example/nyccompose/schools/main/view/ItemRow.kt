package com.example.nyccompose.schools.main.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.SchoolsResultItem

@Composable
fun ItemRow(
    data: SchoolsResultItem,
    onSchoolClick: (SchoolsResultItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimen_16_dp))
            .clickable {
                onSchoolClick(data)
            },
        elevation = dimensionResource(id = R.dimen.dimen_4_dp),
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_8_dp))) {
            Text(
                text = data.schoolName ?: stringResource(id = R.string.no_data_available_TEXT),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = stringResource(id = R.string.about_TEXT),
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_8_dp)),
                style = MaterialTheme.typography.body1
            )
            Divider(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.dimen_4_dp),
                    vertical = dimensionResource(id = R.dimen.dimen_8_dp)
                )
            )
            Text(
                text = data.overviewParagraph
                    ?: stringResource(id = R.string.no_data_available_TEXT),
                style = MaterialTheme.typography.body2
            )
        }
    }
}