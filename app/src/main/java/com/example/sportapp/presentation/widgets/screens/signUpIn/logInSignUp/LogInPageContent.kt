//package com.example.sportapp.presentation.widgets.screens.signUpIn.logInSignUp
//
//import AppActivityViewModel
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.sportapp.R
//import com.example.sportapp.domain.viewModels.authorization.AuthState
//import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
//import com.example.sportapp.presentation.navigation.Screen
//import com.example.sportapp.presentation.widgets.screens.signUpIn.logInSignUp.requests.NotificationPermissionRequest
//import com.example.sportapp.ui.theme.red_accent_color
//import com.example.sportapp.ui.theme.style13LoginPage
//import com.example.sportapp.ui.theme.style14
//import com.example.sportapp.ui.theme.style15
//
//
//@Composable
//fun LogInPageContent(
//    email: MutableState<String>,
//    password: MutableState<String>,
//    authState: AuthState,
//    navController: NavController,
//    authViewModel: AuthViewModel,
//    appActivity: AppActivityViewModel
//) {
//
//    val passwordVisible = remember { mutableStateOf(false) }
//
//    NotificationPermissionRequest()
//
//    Box(modifier = Modifier.padding(20.dp)) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                painter = painterResource(R.drawable.kor_sport_logo),
//                contentDescription = "",
//                modifier = Modifier.height(200.dp)
//            )
//            Text(
//                text = "Log In", style = MaterialTheme.typography.style13LoginPage
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//
//            OutlinedTextField(
//                modifier = Modifier.width(270.dp),
//                value = email.value,
//                onValueChange = { email.value = it },
//                label = { Text(text = "Email") },
//                singleLine = true,
//                colors = TextFieldDefaults.colors(
//                    unfocusedContainerColor = Color.White,
//                    unfocusedTextColor = Color.Black,
//                    focusedContainerColor = Color.White,
//                    focusedTextColor = Color.Black,
//                    unfocusedIndicatorColor = Color.Transparent, // Убираем обводку
//                    focusedIndicatorColor = Color.Transparent,   // Убираем обводку
//                    disabledIndicatorColor = Color.Transparent   // Убираем обводку
//                ),
//                shape = RoundedCornerShape(8.dp)
//            )
//
//
//            OutlinedTextField(
//                modifier = Modifier.width(270.dp),
//                value = password.value,
//                onValueChange = { password.value = it },
//                label = { Text(text = "Password") },
//                singleLine = true,
//                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
//                trailingIcon = {
//                    val image = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
//                    val description = if (passwordVisible.value) "Скрыть пароль" else "Показать пароль"
//                    Icon(
//                        imageVector = image,
//                        contentDescription = description,
//                        modifier = Modifier.clickable {
//                            passwordVisible.value = !passwordVisible.value
//                        }
//                    )
//                },
//                    colors = TextFieldDefaults.colors(
//                    unfocusedContainerColor = Color.White,
//                    unfocusedTextColor = Color.Black,
//                    focusedContainerColor = Color.White,
//                    focusedTextColor = Color.Black,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Transparent,
//                    disabledIndicatorColor = Color.Transparent
//                ),
//                shape = RoundedCornerShape(8.dp)
//            )
//
//            Spacer(Modifier.height(40.dp))
//
//
//            Button(
//                modifier = Modifier
//                    .width(270.dp)
//                    .height(40.dp),
//                onClick = { authViewModel.login(email.value, password.value, appActivity) },
//                enabled = authState != AuthState.Loading,
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = red_accent_color,
//                    contentColor = Color.White
//                )
//            ) {
//                Text(text = "Войти")
//            }
//
//            Spacer(Modifier.height(24.dp))
//
//            Row {
//                Text("Еще нет аккаунта?", style = MaterialTheme.typography.style14)
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(
//                    text = "Зарегистрируйся",
//                    style = style15,
//                    modifier = Modifier.clickable { navController.navigate(Screen.SignupPage.route) }
//                )
//            }
//
//        }
//    }
//}