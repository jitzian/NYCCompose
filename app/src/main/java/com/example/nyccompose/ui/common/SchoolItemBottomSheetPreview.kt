package com.example.nyccompose.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.SchoolsResultItem
import com.example.nyccompose.ui.app.NYCScreen

@Composable
fun <T : SchoolsResultItem> SchoolItemBottomSheetPreview(item: T?, onGoToDetail: (T) -> Unit) {
    if (item != null) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dimen_8_dp)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
        ) {
            Image(
                painter = rememberImagePainter(data = "https://www.pngkey.com/png/full/33-334924_free-icons-png-middle-school-icon-png.png"),
                contentDescription = null,
                modifier = Modifier
                    .width(96.dp)
                    .aspectRatio(1 / 1.5f)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
            ) {
                Text(text = item.schoolName.toString(), style = MaterialTheme.typography.h6)
                Text(text = item.academicopportunities1.toString())
                Button(
                    onClick = { onGoToDetail(item) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = stringResource(id = R.string.go_to_details_TEXT))
                }
            }
        }
    } else {
        Spacer(modifier = Modifier.height(1.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun SchoolItemBottomSheetPreviewPrev() {
    NYCScreen {

        SchoolItemBottomSheetPreview(
            item = SchoolsResultItem().apply {
                schoolName = "Clinton School Writers &amp; Artists, M.S. 260 "
                overviewParagraph =
                    "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities."
                academicopportunities1 = "Free college courses at neighboring universities"

            },
            onGoToDetail = { }
        )
    }
}