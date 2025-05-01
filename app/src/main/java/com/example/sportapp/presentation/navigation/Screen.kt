package com.example.sportapp.presentation.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Matches : Screen("matches")
    data object VideoListPage : Screen("video")
    data object Like : Screen("like")
    data object News : Screen("news/{newsId}")
    data object FirstPage : Screen("firstPage")
    data object LoginPage : Screen("login")
    data object SignupPage : Screen("signup")
    data object Loading : Screen("loading")
    data object VideoPlayerPage : Screen("videoPlayer")
//    data object ImageRegistration : Screen("ImageRegistration")
    data object ProfileSetUpPage : Screen("ProfileSetUpPage")
}