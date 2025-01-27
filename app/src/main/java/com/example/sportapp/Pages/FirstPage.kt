package com.example.sportapp.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.sportapp.Screen
import com.example.sportapp.widgets.first_page.FirstPageElements
import kotlinx.coroutines.delay

@Composable
fun FirstPage(
    navController: NavHostController
) {

    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate(Screen.LoginPage.route)
    }


    FirstPageElements()
}
