package com.example.sportapp.support2026.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
import com.example.sportapp.support2026.presentation.screeens.Screen
import com.example.sportapp.support2026.presentation.screeens.homePage.HomePage

@Composable
fun MainNavigation (
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomePage.route //TODO переделать на страницу входа
    ){
        composable (Screen.HomePage.route)
        {
            HomePage(
                newsViewModel = newsViewModel
            ){
                navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable (Screen.OneNewsPage.route)
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
