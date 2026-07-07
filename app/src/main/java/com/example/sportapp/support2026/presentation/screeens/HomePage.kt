package com.example.sportapp.support2026.presentation.screeens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.sportapp.presentation.widgets.screens.home.home.NewsCardRow
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel

@Composable
fun HomePage(
    newsViewModel: NewsViewModel,
    onNavigateTo: (Screen) -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Home")

        NewsCardRow(
            newsViewModel = newsViewModel,
//            horizontalPaddings = TODO()
        )
    }
}