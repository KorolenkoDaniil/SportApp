package com.example.sportapp.support2026.presentation.screeens.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.support2026.features.user.authorisation.AuthState
import com.example.sportapp.support2026.features.user.authorisation.AuthViewModel
import com.example.sportapp.support2026.presentation.navigation.Screen
import com.example.sportapp.support2026.presentation.ui.theme.background_color
import com.example.sportapp.support2026.presentation.ui.theme.style12FirstPage
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun SplashScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val authStatus by authViewModel.authState.collectAsState()

    LaunchedEffect(authStatus) {
        when (authStatus) {
            is AuthState.Authenticated -> {
                delay(1.seconds)
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            }
            is AuthState.Unauthenticated,
            is AuthState.Error -> {
                delay(1.seconds)
                navController.navigate(Screen.LogInScreen.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            }
            AuthState.Loading -> {
                // Ничего не делаем, ждем смены состояния
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(0.75f)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.kor_sport_logo),
                        contentDescription = "",
                        modifier = Modifier.height(200.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.slogan), style = MaterialTheme.typography.style12FirstPage
                    )
                }
            }


            Box(
                modifier = Modifier
                    .weight(0.15f)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.kor_food),
                        contentDescription = "",
                        modifier = Modifier.height(40.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.korcourses),
                        contentDescription = "",
                        modifier = Modifier.height(40.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.kor_fin2),
                        contentDescription = "",
                        modifier = Modifier.height(40.dp)
                    )
                }
            }
        }
    }
}

