package com.example.nyccompose.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.util.*

sealed class NavItem(
    val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList()
) {
    val route = run {
        //baseRoute/{arg1}/{arg2}

        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object Main : NavItem(AppDestinations.MAIN.name)
    object Detail : NavItem(AppDestinations.DETAIL.name, listOf(NavArg.User, NavArg.RepoName)) {
        fun createNavRoute(user: String, repoName: String) = "$baseRoute/$user/$repoName"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    User("user", NavType.StringType),
    RepoName("repoName", NavType.StringType)
}