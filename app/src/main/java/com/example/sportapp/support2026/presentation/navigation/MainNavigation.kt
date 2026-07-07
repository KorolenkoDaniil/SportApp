package com.example.sportapp.support2026.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
import com.example.sportapp.support2026.presentation.screeens.HomePage
import com.example.sportapp.support2026.presentation.screeens.Screen

@Composable
fun MainNavigation (
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route //TODO переделать на страницу входа
    ){
        composable (Screen.Home.route)
        {
            HomePage(
                newsViewModel = newsViewModel
            ){
                navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
    }

}
