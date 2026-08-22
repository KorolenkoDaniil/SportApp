package com.example.sportapp.support2026.presentation.navigation.bottomNavItem

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun AppBottomBar(
    navController: NavController,
    currentRoute: String?
){
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MatchesList
    )

    val bottomBarRoutes = items.map { it.screen.route }

    if (currentRoute in bottomBarRoutes){
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        if (currentRoute != item.screen.route) {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = { Icon(item.icon, contentDescription = item.title) },
                    label = { Text(item.title) }
                )
            }
        }
    }
}