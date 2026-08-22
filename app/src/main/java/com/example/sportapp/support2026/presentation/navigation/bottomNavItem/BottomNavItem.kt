package com.example.sportapp.support2026.presentation.navigation.bottomNavItem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sportapp.support2026.presentation.navigation.Screen

sealed class BottomNavItem (
    val screen: Screen,
    val title: String,
    val icon: ImageVector
) {

    data object Splash: BottomNavItem(screen = Screen.SplashScreen, title = "SplashScreen", Icons.Default.Home)
    data object LogIn: BottomNavItem(screen = Screen.LogInScreen, title = "LogInScreen", Icons.Default.Home)
    data object SignUp: BottomNavItem(screen = Screen.SignUpScreen, title = "SignUpScreen", Icons.Default.Home)
    data object Home: BottomNavItem(screen = Screen.HomeScreen, title = "Home", Icons.Default.Home)
    data object NewsDetails: BottomNavItem(screen = Screen.NewsDetailsScreen, title = "NewsDetailsScreen", Icons.Default.Home)
    data object MatchesList: BottomNavItem(screen = Screen.MatchesScheduleScreen, title = "MatchesListScreen", Icons.Default.List)
    data object MatchDetails: BottomNavItem(screen = Screen.MatchDetailsScreen, title = "NewsDetailsScreen", Icons.Default.List)
}