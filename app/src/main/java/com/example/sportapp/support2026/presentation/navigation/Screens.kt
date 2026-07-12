package com.example.sportapp.support2026.presentation.navigation

sealed class Screen(val route: String) {
    data object HomePage : Screen("home")
    data object NewsDetailsPage : Screen("oneNewsPage/{newsId}")
}