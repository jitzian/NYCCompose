package com.example.nyccompose.schools.main.view

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.nyccompose.R

@Composable
@Preview(showBackground = true)
fun ItemRow() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimen_8_dp)),
        elevation = dimensionResource(id = R.dimen.dimen_4_dp)
    ) {
        Column {
            Text(
                text = "Clinton School Writers & Artists, M.S. 260",
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "About",
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
                text = "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities.",
                style = MaterialTheme.typography.body2
            )
        }
    }
}