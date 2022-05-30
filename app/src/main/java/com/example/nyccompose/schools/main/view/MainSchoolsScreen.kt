package com.example.nyccompose.schools.main.view

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.SchoolsResultItem
import com.example.nyccompose.schools.main.viewmodel.MainSchoolViewModel
import com.example.nyccompose.ui.app.NYCScreen
import com.example.nyccompose.ui.common.MainAppBar
import com.example.nyccompose.ui.common.SchoolItemBottomSheetPreview
import com.example.nyccompose.ui.common.error.ConnectivityError
import com.example.nyccompose.ui.common.loading.LoadingScreen
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun MainSchoolScreenState(
    mainSchoolViewModel: MainSchoolViewModel = viewModel(),
    onSchoolClick: (SchoolsResultItem) -> Unit
) {
    val data by mainSchoolViewModel.data.collectAsState()
    mainSchoolViewModel.fetchData()

    when (data) {
        is MainSchoolViewModel.UIState.Loading -> {
            LoadingScreen()
        }
        is MainSchoolViewModel.UIState.Success -> {
            MainSchoolsScreenScreen(
                schools = (data as MainSchoolViewModel.UIState.Success).listOfSchools,
                onSchoolClick = onSchoolClick,
                onItemMore = onSchoolClick
            )
        }
        is MainSchoolViewModel.UIState.Error -> {
            ConnectivityError(message = stringResource(id = R.string.an_error_occurred_TEXT))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun <T : SchoolsResultItem> MainSchoolsScreenScreen(
    schools: List<T>,
    onSchoolClick: (T) -> Unit,
    onItemMore: (T) -> Unit
) {
    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val showScrollToTopButton by remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0
        }
    }
    //This is for handling the state / visibility of the BottomSheet
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    BackPressedHandler(sheetState.isVisible) {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    NYCScreen {
        Scaffold(
            topBar = {
                MainAppBar(stringResource(id = R.string.new_york_cities_schools_TEXT))
            },
            floatingActionButton = {
                if (showScrollToTopButton) {
                    FloatingActionButton(onClick = {
                        coroutineScope.launch {
                            state.animateScrollToItem(index = 0)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowUpward,
                            stringResource(id = R.string.go_to_top_TEXT)
                        )
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {

            var bottomSheetItem by remember {
                mutableStateOf<T?>(null)
            }

            ModalBottomSheetLayout(
                sheetContent = {
                    SchoolItemBottomSheetPreview(
                        item = bottomSheetItem,
                        //onGoToDetail = onSchoolClick
                        onGoToDetail = {
                            coroutineScope.launch {
                                sheetState.hide()
                                onSchoolClick(it)
                            }
                        }
                    )
                },
                sheetState = sheetState
            ) {
                LazyColumn(state = state) {
                    items(schools) { school ->
                        ItemRow(
                            data = school,
                            onSchoolClick = onSchoolClick,
                            onItemMore = {
                                bottomSheetItem = it
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BackPressedHandler(enabled: Boolean, onBack: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val backDispatcher =
        requireNotNull(LocalOnBackPressedDispatcherOwner.current).onBackPressedDispatcher

    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }
    SideEffect {
        backCallback.isEnabled = enabled
    }

    DisposableEffect(key1 = backDispatcher, key2 = lifecycleOwner) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose { backCallback.remove() }
    }
}