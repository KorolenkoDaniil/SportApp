package com.example.firebaseexample.pages

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.sportapp.models.viewModels.AuthState
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.pages.Screen
import com.example.sportapp.pages.widgets.SignupPageContent


@Composable
fun SignupPage(navController: NavController, authViewModel: AuthViewModel) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val authState = authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate(Screen.Home.route)
            is AuthState.Error -> Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    SignupPageContent(
        email = email,
        password = password,
        authViewModel = authViewModel,
        authState = authState,
        navController = navController,
        context
    )
}




















