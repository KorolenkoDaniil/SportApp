package com.example.sportapp.support2026.presentation.navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home")
    data object SplashScreen : Screen("splash")
    data object NewsDetailsScreen : Screen("oneNewsPage/{newsId}")

    data object  SignUpScreen : Screen("signUp")
    data object  LogInScreen : Screen("logIn")

}