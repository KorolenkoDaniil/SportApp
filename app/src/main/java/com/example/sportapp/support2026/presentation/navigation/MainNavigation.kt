package com.example.sportapp.support2026.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.example.sportapp.support2026.features.user.authorisation.AuthViewModel
import com.example.sportapp.support2026.features.user.viewModel.UserViewModel
import com.example.sportapp.support2026.presentation.navigation.bottomNavItem.AppBottomBar
import com.example.sportapp.support2026.presentation.screeens.homeScreen.HomeScreen
import com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.NewsDetailsScreen
import com.example.sportapp.support2026.presentation.screeens.logInScreen.LogInScreen
import com.example.sportapp.support2026.presentation.screeens.matchesScheduleScreen.MatchesScheduleScreen
import com.example.sportapp.support2026.presentation.screeens.matchesScheduleScreen.matchDetailsScreen.MatchDetailsScreen
import com.example.sportapp.support2026.presentation.screeens.signUpScreen.SignUpScreen
import com.example.sportapp.support2026.presentation.screeens.splashScreen.SplashScreen

@Composable
fun MainNavigation (
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel(),
) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isAuthenticated by userViewModel.isAuthenticated.collectAsState()
    val currentUser by userViewModel.currentUser.collectAsState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            AppBottomBar(
                navController = navHostController,
                currentRoute = currentRoute
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navHostController,
            startDestination = "auth_graph",
            modifier = Modifier.padding(innerPadding)
        ) {

            navigation(
                startDestination = Screen.SplashScreen.route,
                route = "auth_graph"
            ) {

                composable(Screen.SplashScreen.route) {
                    val parentEntry = remember(navBackStackEntry) {
                        navHostController.getBackStackEntryOrNull("auth_graph")
                    }
                    if (parentEntry != null) {
                        val authViewModel: AuthViewModel = hiltViewModel(parentEntry)
                        SplashScreen(
                            navController = navHostController,
                            authViewModel = authViewModel
                        )
                    }
                }

                composable(Screen.LogInScreen.route) {
                    val parentEntry = remember(navBackStackEntry) {
                        navHostController.getBackStackEntryOrNull("auth_graph")
                    }
                    if (parentEntry != null) {
                        val authViewModel: AuthViewModel = hiltViewModel(parentEntry)
                        LogInScreen(
                            userViewModel = userViewModel,
                            navController = navHostController,
                            authViewModel = authViewModel
                        )
                    }
                }

                composable(Screen.SignUpScreen.route) {
                    val parentEntry = remember(navBackStackEntry) {
                        navHostController.getBackStackEntryOrNull("auth_graph")
                    }
                    if (parentEntry != null) {
                        val authViewModel: AuthViewModel = hiltViewModel(parentEntry)
                        SignUpScreen(
                            userViewModel = userViewModel,
                            navController = navHostController,
                            authViewModel = authViewModel
                        )
                    }
                }
            }

            composable(Screen.HomeScreen.route) {
                HomeScreen(
                    navController = navHostController,
                    userViewModel = userViewModel
                )
            }

            composable(
                route = Screen.NewsDetailsScreen.route,
                arguments = listOf(
                    navArgument("newsId") {
                        type = NavType.IntType
                    }
                ),
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "korsport://news/{newsId}"
                    },
                    navDeepLink {
                        uriPattern =
                            "https://korolenkodaniil.github.io/deeplink-sportapp/?id={newsId}"
                    }
                )
            ) { backStackEntry ->
                val newsId = backStackEntry.arguments?.getInt("newsId")
                    ?: backStackEntry.arguments?.getString("newsId")?.toIntOrNull()

                if (newsId != null && newsId != -1) {

                    NewsDetailsScreen(
                        navController = navHostController,
                        newsId = newsId,
                        user = currentUser,
                        isAuthenticated = isAuthenticated,
                    )
                } else {
                    Log.d("MainNavigation", "не правильный аргумент newsId")
                }
            }

            composable(Screen.MatchesScheduleScreen.route) {
                MatchesScheduleScreen(
                    navController = navHostController
                )
            }

            composable(
                route = Screen.MatchDetailsScreen.route,
                arguments = listOf(
                    navArgument("matchId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val matchId = backStackEntry.arguments?.getInt("matchId")
                MatchDetailsScreen(
                    matchId = matchId
                )
            }
        }
    }
}


fun NavHostController.getBackStackEntryOrNull(route: String): NavBackStackEntry? {
    return try {
        getBackStackEntry(route)
    } catch (e: IllegalArgumentException) {
        null
    }
}