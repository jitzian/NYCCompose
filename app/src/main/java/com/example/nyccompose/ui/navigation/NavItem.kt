package com.example.nyccompose.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.nyccompose.R
import com.example.nyccompose.rest.model.SchoolsResultItem

sealed class NavItem(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList()
) {

    class ContentType(feature: Feature) : NavItem(feature)

    class ContentDetail(feature: Feature) : NavItem(feature, "detail", listOf(NavArg.Dbn)) {
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