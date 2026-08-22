package com.example.sportapp.support2026.presentation.screeens.signUpScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.sportapp.support2026.features.user.authorisation.AuthState
import com.example.sportapp.support2026.features.user.authorisation.AuthViewModel
import com.example.sportapp.support2026.features.user.viewModel.UserViewModel
import com.example.sportapp.support2026.presentation.screeens.signUpScreen.content.SignUpScreenContent

@Composable
fun SignUpScreen(
    userViewModel: UserViewModel,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {


    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        Log.d("SignUpScreen", "Current authState: $authState")

        when (val state = authState) {
            is AuthState.Authenticated -> {
                userViewModel.loadUser(
                    email = state.email
                )
            }
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    state.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            AuthState.Unauthenticated -> {
                // Готов к вводу
            }
            AuthState.Loading -> {
                // Показ индикатора загрузки на UI
            }
        }
    }

//
//    val context = LocalContext.current
//    var passwordVisible by remember { mutableStateOf(false) }
//
//    LaunchedEffect(authState) {
//        when (authState) {
//            is AuthState.Authenticated -> {
//                navController.navigate(Screen.HomeScreen.route) {
//                    popUpTo(Screen.SignUpScreen.route) { inclusive = true }
//                }
//            }
//            is AuthState.Error -> {
//                Toast.makeText(context, authState.message, Toast.LENGTH_SHORT).show()
//            }
//            else -> {}
//        }
//    }

    SignUpScreenContent(
        email = email.value,
        onEmailChange = {email.value = it},
        password = password.value,
        onPasswordChange = {password.value = it},
        authState = authState,
        authViewModel = authViewModel,
        navController = navController,
    )



}