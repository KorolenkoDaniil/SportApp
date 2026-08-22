package com.example.sportapp.support2026.presentation.screeens.logInScreen.content

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
import com.example.sportapp.support2026.presentation.ui.theme.style14
import com.example.sportapp.support2026.presentation.ui.theme.style15
import com.example.sportapp.support2026.presentation.ui.width_270dp

//todo вынести все фугкции, что надо делать по клику и передавать их
//todo разнести элементы по виджетам


@Composable
fun LogInContent (
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
                // todo сделаать адаптиваность
            )

            Text(
                text = stringResource(id = R.string.Log_in_rus),
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.height(height_16dp))


            AppTextField(
                value = email,
                onValueChange = onEmailChange,
                label = stringResource(R.string.Email_rus),
                modifier = Modifier.width(width_270dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            PasswordTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.Password_rus),
                modifier = Modifier.width(width_270dp),
            )

//            OutlinedTextField(
//                modifier = Modifier.width(width_270dp),
//                //todo вынести размеры
//                value = password.value,
//                onValueChange = { password.value = it },
//                label = { Text(text = "Password") },
//                //todo вынести текст
//                singleLine = true,
//                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
//                trailingIcon = {
//                    val image = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
//                    val description = if (passwordVisible.value)
//                        "Скрыть пароль" else "Показать пароль"
//                    //todo вынести текст
//                    Icon(
//                        imageVector = image,
//                        contentDescription = description,
//                        modifier = Modifier.clickable {
//                            passwordVisible.value = !passwordVisible.value
//                        }
//                    )
//                },
//                colors = TextFieldDefaults.colors(
//                    unfocusedContainerColor = Color.White,
//                    unfocusedTextColor = Color.Black,
//                    focusedContainerColor = Color.White,
//                    focusedTextColor = Color.Black,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                    disabledIndicatorColor = Color.Transparent
//                ),
//                shape = RoundedCornerShape(8.dp)
//                //todo вынести размеры
//            )

            Spacer(Modifier.height(40.dp))
            //todo вынести размеры


            Button(
                modifier = Modifier
                    .width(270.dp)
                    .height(40.dp),
                //todo вынести размеры

                onClick = {
                    authViewModel.login(
                        emailInput = email,
                        passwordInput = password
                )},

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
                    Text(text = "Войти")
                }
            }

            Spacer(Modifier.height(24.dp))
            //todo вынести размеры

            Row {
                Text("Еще нет аккаунта?", style = MaterialTheme.typography.style14)
                //todo вынести текст
                Spacer(modifier = Modifier.width(4.dp))
                //todo вынести текст
                Text(
                    text = "Зарегистрируйся",
                    style = style15,
                    modifier = Modifier.clickable { navController.navigate(Screen.SignUpScreen.route) }
                )
            }

        }
    }
}