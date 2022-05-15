package com.example.nyccompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import coil.annotation.ExperimentalCoilApi
import com.example.nyccompose.schools.detail.view.SchoolDetailScreenState
import com.example.nyccompose.schools.main.view.MainSchoolScreenState

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
//TODO: Rename this...
fun NavigationConfig() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        //startDestination = NavItem.ContentType(Feature.SCHOOLS).route
    startDestination = Feature.SCHOOLS.route
    ) {
        schoolsNav(navController)
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.schoolsNav(navController: NavController) {

    navigation(
        startDestination = NavItem.ContentType(Feature.SCHOOLS).route,
        route = Feature.SCHOOLS.route
    ) {
        composable(NavItem.ContentType(Feature.SCHOOLS)) {
            MainSchoolScreenState(
                onSchoolClick = { school ->
                    school.dbn.let { safeDbn ->
                        navController.navigate(
                            NavItem.ContentDetail(Feature.SCHOOLS).createRoute(safeDbn)
                        )
                    }
                }
            )
        }

        composable(NavItem.ContentDetail(Feature.SCHOOLS)) {
            SchoolDetailScreenState(
                onUpClick = {
                    navController.navigateUp()
                })
        }
    }

}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
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