package com.example.sportapp.support2026.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
            ){

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

            // ИСПРАВЛЕННЫЙ БЛОК ДЛЯ НОВОСТЕЙ С ПОДДЕРЖКОЙ ДИПЛИНКОВ И ПЕРЕХОДОВ
            composable(
                route = Screen.NewsDetailsScreen.route,
                arguments = listOf(
                    navArgument("newsId") {
                        type = NavType.IntType
                    }
                ),
                deepLinks = listOf(
                    // Поддержка прямого диплинка из манифеста
                    navDeepLink {
                        uriPattern = "korsport://news/{newsId}"
                    },
                    // Поддержка диплинка через ваш веб-шлюз на GitHub Pages
                    navDeepLink {
                        uriPattern = "https://korolenkodaniil.github.io/deeplink-sportapp/?id={newsId}"
                    }
                )
            ) { backStackEntry ->
                // Универсальное извлечение: сработает и при обычной навигации, и при диплинке
                val newsId = backStackEntry.arguments?.getInt("newsId")
                    ?: backStackEntry.arguments?.getString("newsId")?.toIntOrNull()

                if (newsId != null && newsId != -1) {
                    NewsDetailsScreen(navHostController, newsId)
                } else {
                    Log.d("MainNavigation", "не правильный аргумент newsId")
                }
            }

            composable(Screen.MatchesScheduleScreen.route) {
                MatchesScheduleScreen()
            }

            composable(Screen.MatchDetailsScreen.route) {
                MatchDetailsScreen()
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