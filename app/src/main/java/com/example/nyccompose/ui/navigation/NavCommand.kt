package com.example.nyccompose.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pages
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Score
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.nyccompose.R

enum class NavItem(
    val navCommand: NavCommand,
    val icon: ImageVector,
    @StringRes val title: Int
) {
    SCHOOLS(NavCommand.ContentType(Feature.SCHOOLS), Icons.Default.School, R.string.schools_TEXT),
    SCORES(NavCommand.ContentType(Feature.SCORES), Icons.Default.Score, R.string.scores_TEXT),
    REQUIREMENT(NavCommand.ContentType(Feature.REQUIREMENTS), Icons.Default.Pages, R.string.requirements_TEXT)
}

sealed class NavCommand(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList()
) {

    class ContentType(feature: Feature) : NavCommand(feature)

    class ContentDetail(feature: Feature) : NavCommand(feature, "detail", listOf(NavArg.Dbn)) {
        fun createRoute(dbn: String) = "${feature.route}/$subRoute/$dbn"
    }

    val route = run {
        //baseRoute/{arg1}/{arg2}
        val argKeys = navArgs.map { "{${it.key}}" }
        //listOf(feature.route, subRoute)
        listOf(feature.route)
            .plus(subRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

}

enum class NavArg(val key: String, val navType: NavType<*>) {
    User("user", NavType.StringType),
    RepoName("repoName", NavType.StringType),
    Dbn("dbn", NavType.StringType),
}