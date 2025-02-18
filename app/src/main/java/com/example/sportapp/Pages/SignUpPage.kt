package com.example.firebaseexample.pages

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sportapp.R
import com.example.sportapp.Screen
import com.example.sportapp.models.user.AuthState
import com.example.sportapp.models.user.AuthViewModel
import com.example.sportapp.ui.theme.red_accent_color
import com.example.sportapp.ui.theme.style13_log_in__page
import com.example.sportapp.ui.theme.style14
import com.example.sportapp.ui.theme.style15


@Composable
fun SignupPage(navController: NavController, authViewModel: AuthViewModel) {


    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate(Screen.Home.route)
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }



    Box(modifier = Modifier.padding(20.dp)) {
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
                text = "Sign up", style = style13_log_in__page
            )

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                modifier = Modifier.width(270.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )



            OutlinedTextField(
                modifier = Modifier.width(270.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(Modifier.height(40.dp))


            Button(
                modifier = Modifier
                    .width(270.dp)
                    .height(40.dp),
                onClick = {
                    authViewModel.signup(email, password)







                }, enabled = authState.value != AuthState.Loading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = red_accent_color,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Создать аккаунт")
            }




            Spacer(Modifier.height(24.dp))

            Row {
                Text("Уже есть аккаунт?", style = style14)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Авторизуйся",
                    style = style15,
                    modifier = Modifier.clickable { navController.navigate(Screen.LoginPage.route) }
                )
            }

        }
    }
}





















