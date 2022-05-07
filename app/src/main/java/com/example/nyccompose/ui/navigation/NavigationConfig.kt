package com.example.nyccompose.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.nyccompose.schools.main.view.MainSchoolScreenState

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
//TODO: Rename this...
fun NavigationConfig() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.Main.route
    ) {
        composable(NavItem.Main) {
//            MainScreenState { user, repoName ->
//                navController.navigate(NavItem.Detail.createNavRoute(user, repoName))
//            }
            MainSchoolScreenState()
        }
        composable(NavItem.Detail) { backStackEntry ->
//            DetailScreenState(
//                user = backStackEntry.findArg(NavArg.User),
//                repoName = backStackEntry.findArg(NavArg.RepoName),
//                onUpClick = {
//                    navController.navigateUp()
//                }
//            )
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

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value = value)
    return value as T
}