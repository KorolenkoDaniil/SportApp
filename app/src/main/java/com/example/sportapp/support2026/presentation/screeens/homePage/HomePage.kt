package com.example.sportapp.support2026.presentation.screeens.homePage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
import com.example.sportapp.support2026.presentation.screeens.homePage.widgets.NewsCardRow

@Composable
fun HomePage(
    newsViewModel: NewsViewModel,
    navController: NavHostController
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Home")

        NewsCardRow(
            newsViewModel = newsViewModel,
            navController = navController
        )
    }
}