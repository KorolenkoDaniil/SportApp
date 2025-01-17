package com.example.sportapp.widgets.home

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.sportapp.models.news.domain.NewsEntity
import com.example.sportapp.shared.NewsCard

@Composable

fun NewsCardRow (newsList: List<NewsEntity>,  navController: NavHostController){
    LazyRow {
        items(newsList.size) { index ->

            NewsCard(newsList[index], navController)
        }
    }
}

