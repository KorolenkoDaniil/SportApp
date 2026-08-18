package com.example.sportapp.support2026.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
import com.example.sportapp.support2026.features.user.authorisation.AuthViewModel
import com.example.sportapp.support2026.features.user.viewModel.UserViewModel
import com.example.sportapp.support2026.presentation.screeens.homeScreen.HomeScreen
import com.example.sportapp.support2026.presentation.screeens.logInScreen.LogInScreen
import com.example.sportapp.support2026.presentation.screeens.newsDetailsScreen.NewsDetailsScreen
import com.example.sportapp.support2026.presentation.screeens.splashScreen.SplashScreen

@Composable
fun MainNavigation (
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    newsViewModel: NewsViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route
    ){


        composable (Screen.HomeScreen.route)
        {
            HomeScreen(
                newsViewModel = newsViewModel,
                navController = navHostController
            )
        }

        composable (Screen.NewsDetailsScreen.route)
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


        composable (Screen.SplashScreen.route) {
            SplashScreen(
                navController = navHostController,
                authViewModel = authViewModel
            )
        }

        composable (Screen.LogInScreen.route){
            LogInScreen(
                userViewModel,
                navController = navHostController,
                authViewModel
            )
        }

    }

}


