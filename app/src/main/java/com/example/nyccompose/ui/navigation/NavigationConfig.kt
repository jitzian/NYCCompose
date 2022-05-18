package com.example.nyccompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.nyccompose.schools.detail.view.SchoolDetailScreenState
import com.example.nyccompose.schools.main.view.MainSchoolScreenState
import com.example.nyccompose.schools.scores.view.ScoresScreenState

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
//TODO: Rename this...
fun NavigationConfig(navController: NavHostController) {
    NavHost(
        navController = navController,
        //startDestination = NavItem.ContentType(Feature.SCHOOLS).route
        startDestination = Feature.SCHOOLS.route
    ) {
        schoolsNav(navController)
        scoresNav(navController)
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.schoolsNav(navController: NavController) {

    navigation(
        startDestination = NavCommand.ContentType(Feature.SCHOOLS).route,
        route = Feature.SCHOOLS.route
    ) {
        composable(NavCommand.ContentType(Feature.SCHOOLS)) {
            MainSchoolScreenState(
                onSchoolClick = { school ->
                    school.dbn.let { safeDbn ->
                        navController.navigate(
                            NavCommand.ContentDetail(Feature.SCHOOLS).createRoute(safeDbn)
                        )
                    }
                }
            )
        }

        composable(NavCommand.ContentDetail(Feature.SCHOOLS)) {
            SchoolDetailScreenState(
                onUpClick = {
                    navController.navigateUp()
                })
        }
    }

}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.scoresNav(navController: NavController) {

    navigation(
        startDestination = NavCommand.ContentType(Feature.SCORES).route,
        route = Feature.SCORES.route
    ) {
        composable(NavCommand.ContentType(Feature.SCORES)) {
            ScoresScreenState(onUpClick = { navController.navigateUp() })
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavCommand,
    content: @Composable (NavBackStackEntry) -> (Unit)
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

//private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
//    val value = arguments?.get(arg.key)
//    requireNotNull(value = value)
//    return value as T
//}