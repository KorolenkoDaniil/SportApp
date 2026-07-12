package com.example.sportapp.support2026.presentation.screeens

sealed class Screen(val route: String) {
    data object HomePage : Screen("home")
    data object OneNewsPage : Screen("oneNewsPage")
}