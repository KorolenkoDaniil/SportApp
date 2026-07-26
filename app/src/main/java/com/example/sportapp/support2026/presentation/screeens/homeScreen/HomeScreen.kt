package com.example.sportapp.support2026.presentation.screeens.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sportapp.support2026.features.news.presentation.NewsState
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
import com.example.sportapp.support2026.presentation.screeens.ScreenWrapper
import com.example.sportapp.support2026.presentation.screeens.homeScreen.widgets.NewsCardRow
import com.example.sportapp.support2026.presentation.screeens.sharedWidgets.LoadingWidget

@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel,
    navController: NavHostController
){
    val newsState by newsViewModel.state.collectAsState()

    when (val state = newsState){
        is NewsState.Load -> {
            LoadingWidget()
        }
        is NewsState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Ошибка: ${state.e.localizedMessage}")
            }
        }

        is NewsState.NewsContent -> {
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
    }
}