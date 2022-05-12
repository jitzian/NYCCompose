package com.example.nyccompose.schools.detail.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.nyccompose.R
import com.example.nyccompose.ui.app.NYCApp
import com.example.nyccompose.ui.common.MainAppBar

@Composable
@Preview(showBackground = true)
//TODO: Remove hardcoded values
fun SchoolDetailScreen() {
    NYCApp {
        SchoolDetailScaffold {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.dimen_16_dp))
            ) {
                Text(
                    text = "Clinton School Writers &amp; Artists, M.S. 260",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities.",
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
                    text = stringResource(id = R.string.location_TEXT),
                    modifier = Modifier.padding(
                        vertical = dimensionResource(
                            id = R.dimen.dimen_8_dp
                        )
                    )
                )
                Text(
                    text = "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)",
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