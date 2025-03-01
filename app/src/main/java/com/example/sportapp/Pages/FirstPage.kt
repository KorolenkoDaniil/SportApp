package com.example.sportapp.pages

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.sportapp.Screen
import com.example.sportapp.models.viewModels.AuthState

import com.example.sportapp.widgets.first_page.FirstPageElements

@Composable
fun FirstPage(
    navController: NavHostController,
    authState: AuthState
) {
    Log.d("FirstPage", "Auth state: $authState")

    FirstPageElements()

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                Log.d("FirstPage", "Navigating to Home")
                navController.navigate(Screen.Home.route) {
                    launchSingleTop = true
                    popUpTo(Screen.FirstPage.route) { inclusive = true }
                }
            }

            is AuthState.Unauthenticated -> {
                Log.d("FirstPage", "Navigating to LoginPage")
                navController.navigate(Screen.LoginPage.route) {
                    launchSingleTop = true
                    popUpTo(Screen.FirstPage.route) { inclusive = true }
                }
            }

            is AuthState.Error -> Log.d("FirstPage", "Authentication error")
            AuthState.Loading -> Log.d("FirstPage", "Authentication loading")
        }
    }
}
