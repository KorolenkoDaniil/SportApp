package com.example.sportapp.support2026.presentation.screeens

sealed class Screen(val route: String) {
    data object Home : Screen("home")
}