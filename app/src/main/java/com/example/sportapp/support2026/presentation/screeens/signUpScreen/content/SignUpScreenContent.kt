package com.example.sportapp.support2026.presentation.screeens.signUpScreen.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.support2026.features.user.authorisation.AuthState
import com.example.sportapp.support2026.features.user.authorisation.AuthViewModel
import com.example.sportapp.support2026.presentation.navigation.Screen
import com.example.sportapp.support2026.presentation.screeens.commonWidgets.AppTextField
import com.example.sportapp.support2026.presentation.screeens.commonWidgets.passwordTextInput.PasswordTextField
import com.example.sportapp.support2026.presentation.ui.height_16dp
import com.example.sportapp.support2026.presentation.ui.paddings_20dp
import com.example.sportapp.support2026.presentation.ui.theme.red_accent_color
import com.example.sportapp.support2026.presentation.ui.theme.style13LoginPage
import com.example.sportapp.support2026.presentation.ui.theme.style14
import com.example.sportapp.support2026.presentation.ui.theme.style15
import com.example.sportapp.support2026.presentation.ui.width_270dp

@Composable
fun SignUpScreenContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    authViewModel: AuthViewModel,
    authState: AuthState,
    navController: NavHostController,
) {


    Box(modifier = Modifier.padding(paddings_20dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.kor_sport_logo),
                contentDescription = "",
                modifier = Modifier.height(200.dp)
            )

            Text(
                text = stringResource(R.string.Sign_up_rus),
                style = MaterialTheme.typography.style13LoginPage
            )

            Spacer(modifier = Modifier.height(height_16dp))

            AppTextField(
                modifier = Modifier.width(270.dp),
                value = email,
                onValueChange = onEmailChange,
                label = stringResource(R.string.Email_rus),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )

            Spacer(modifier = Modifier.height(8.dp))

            PasswordTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.Password_rus),
                modifier = Modifier.width(width_270dp),
            )

            Spacer(Modifier.height(40.dp))

            Button(
                modifier = Modifier
                    .width(270.dp)
                    .height(40.dp),
                onClick = {
                    authViewModel.signup(
                        email,
                        password)
                },
                enabled = authState != AuthState.Loading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = red_accent_color,
                    contentColor = Color.White
                )
            ) {
                if (authState is AuthState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.height(24.dp),
                        color = Color.White
                    )
                } else {
                    Text(text = "Создать аккаунт")
                }
            }

            Spacer(Modifier.height(24.dp))

            Row {
                Text("Уже есть аккаунт?", style = MaterialTheme.typography.style14)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Авторизуйся",
                    style = style15,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.LogInScreen.route)
                    }
                )
            }
        }
    }
}