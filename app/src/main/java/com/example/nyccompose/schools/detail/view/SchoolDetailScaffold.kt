package com.example.nyccompose.schools.detail.view

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.SchoolsResultItem
import com.example.nyccompose.schools.detail.viewmodel.SchoolDetailViewModel
import com.example.nyccompose.ui.common.MainAppBar

@Composable
fun SchoolDetailScaffoldState(
    onUpClick: () -> Unit,
    schoolDetailViewModel: SchoolDetailViewModel = viewModel(),
    content: @Composable (PaddingValues) -> Unit
) {
    val data by schoolDetailViewModel.state.collectAsState()

    when (data) {
        is SchoolDetailViewModel.UIState.Success -> {
            SchoolDetailScaffold(
                onUpClick = onUpClick,
                content = content,
                school = (data as SchoolDetailViewModel.UIState.Success).school
            )
        }
        is SchoolDetailViewModel.UIState.Error -> {

        }
        else -> Unit
    }

}


@Composable
fun SchoolDetailScaffold(
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    school: SchoolsResultItem
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            MainAppBar(
                title = stringResource(id = R.string.detail_of_school_TEXT),
                showBackButton = true,
                onUpClick = onUpClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                shareSchool(context, school)
            }) {
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