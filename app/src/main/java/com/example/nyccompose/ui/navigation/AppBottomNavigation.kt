package com.example.nyccompose.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun AppBottomNavigation(currentRoute: String, onNavItemClick: (NavItem) -> Unit) {
    BottomNavigation {
        NavItem.values().forEach { item ->
            val title = stringResource(item.title)
            BottomNavigationItem(
                selected = currentRoute.contains(item.navCommand.feature.route),
                onClick = { onNavItemClick(item) },
                /*{
                    navController.navigatePoppingUpToStartDestination(item.navCommand.route)
                },*/
                icon = {
                    Icon(imageVector = item.icon, contentDescription = title)
                },
                label = {
                    Text(text = title)
                }
            )
        }
    }
}