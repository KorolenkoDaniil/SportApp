package com.example.sportapp.support2026.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
import com.example.sportapp.support2026.presentation.screeens.NewsDetailsPage.NewsDetailsScreen
import com.example.sportapp.support2026.presentation.screeens.homePage.HomeScreen

@Composable
fun MainNavigation (
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    newsViewModel: NewsViewModel = hiltViewModel(),
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomePage.route //TODO переделать на страницу входа
    ){
        composable (Screen.HomePage.route)
        {
            HomeScreen(
                newsViewModel = newsViewModel,
                navController = navHostController
            )
        }

        composable (Screen.NewsDetailsPage.route)
        {
            backStackEntry ->
            val newsId = backStackEntry.arguments?.getString("newsId")?.toIntOrNull()

            if (newsId != null) {
                NewsDetailsScreen(navHostController, newsId)
            }
            else{
                Log.d("MainNavigation", "не правильный аргумент newsId")
            }
        }

    }

}


