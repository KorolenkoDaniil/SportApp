package com.example.sportapp.support2026.presentation.screeens.signUpScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.sportapp.support2026.features.user.viewModel.UserViewModel

@Composable
fun SignUpScreen(
    userViewModel: UserViewModel,
    navController: NavHostController
) {



    Column() {
        Text("sdsdfsdfsdf")

//        OutlinedTextField(
//            modifier = Modifier.width(270.dp),
//            value = authViewModel.email.value,
//            onValueChange = { authViewModel.email.value = it },
//            label = { Text(text = "Email") },
//            singleLine = true,
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Color.White,
//                unfocusedTextColor = Color.Black,
//                focusedContainerColor = Color.White,
//                focusedTextColor = Color.Black,
//                unfocusedIndicatorColor = Color.Transparent,
//                focusedIndicatorColor = Color.Transparent,
//                disabledIndicatorColor = Color.Transparent
//            ),
//            shape = RoundedCornerShape(8.dp)
//        )
//        Button(
//            onClick = userViewModel.signUp(
//                email = TODO(),
//                password = TODO()
//            ),
//        ) { }
    }



}