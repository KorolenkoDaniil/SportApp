package com.example.sportapp.support2026.presentation.screeens.homePage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
import com.example.sportapp.support2026.presentation.screeens.ScreenWrapper
import com.example.sportapp.support2026.presentation.screeens.homePage.widgets.NewsCardRow

@Composable
fun HomePage(
    newsViewModel: NewsViewModel,
    navController: NavHostController
){

    ScreenWrapper(
        content = {
            Text(text = "Home")

            NewsCardRow(
                newsViewModel = newsViewModel,
                navController = navController
            )
        }
    )
}