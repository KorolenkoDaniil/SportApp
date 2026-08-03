package com.example.sportapp.support2026.presentation.screeens.logInScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.sportapp.support2026.features.user.viewModel.UserViewModel
import com.example.sportapp.support2026.presentation.navigation.Screen

@Composable
fun LogInScreen(
    userViewModel: UserViewModel,
    navController: NavHostController
) {

    LaunchedEffect(Unit) {

        userViewModel


        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.SplashScreen.route) { inclusive = true }
        }
    }

}