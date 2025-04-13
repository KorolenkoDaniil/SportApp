package com.example.sportapp.presentation.widgets.screens.signUpIn

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.sportapp.models.viewModels.AuthState
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.presentation.widgets.screens.signUpIn.logInSignUp.LogInPageContent
import com.example.sportapp.presentation.navigation.Screen

@Composable
fun LoginPage(navController: NavController, authViewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {

        Log.d("tttt", authState.toString())

        when (authState) {
            is AuthState.Authenticated -> {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.LoginPage.route) { inclusive = true }
                }
            }

            is AuthState.Unauthenticated -> {}
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState as AuthState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> Unit
        }
    }


    LogInPageContent(
        email = email,
        password = password,
        authState = authState,
        navController = navController,
        authViewModel = authViewModel
    )
}
