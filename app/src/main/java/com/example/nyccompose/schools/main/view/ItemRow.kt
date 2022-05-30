package com.example.nyccompose.schools.main.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.SchoolsResultItem

@Composable
fun <T: SchoolsResultItem> ItemRow(
    data: T,
    onSchoolClick: (T) -> Unit,
    onItemMore: (T) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_16_dp),
                vertical = dimensionResource(id = R.dimen.dimen_8_dp)
            )
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
            IconButton(
                onClick = {
                    onItemMore(data)
                },
                modifier = Modifier.align(alignment = Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.more_actions_TEXT)
                )
            }
        }
    }
}