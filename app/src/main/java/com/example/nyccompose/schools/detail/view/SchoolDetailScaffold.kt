package com.example.nyccompose.schools.detail.view

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.SchoolsResultItem
import com.example.nyccompose.ui.common.MainAppBar

@Composable
fun SchoolDetailScaffold(
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            MainAppBar(
                title = "Detail of School",
                showBackButton = true
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(id = R.string.share_school_details_TEXT)
                )
            }
        },
        content = content
    )
}

fun shareSchool(context: Context, school: SchoolsResultItem) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setText(school.website)
        .intent
        .also(context::startActivity)
}