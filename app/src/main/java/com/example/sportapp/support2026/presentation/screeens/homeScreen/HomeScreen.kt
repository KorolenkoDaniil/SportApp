package com.example.sportapp.support2026.presentation.screeens.homeScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sportapp.support2026.features.news.presentation.NewsState
import com.example.sportapp.support2026.features.news.presentation.NewsViewModel
import com.example.sportapp.support2026.features.soccer.viewModel.SoccerViewModel
import com.example.sportapp.support2026.features.user.viewModel.UserViewModel
import com.example.sportapp.support2026.presentation.screeens.ScreenWrapper
import com.example.sportapp.support2026.presentation.screeens.homeScreen.widgets.NewsCardRow
import com.example.sportapp.support2026.presentation.screeens.homeScreen.widgets.homeTopBar.HomeTopBar
import com.example.sportapp.support2026.presentation.screeens.sharedWidgets.LoadingWidget

@Composable
fun HomeScreen(
    navController: NavHostController,
    userViewModel: UserViewModel,
    soccerViewModel: SoccerViewModel = hiltViewModel(),
    newsViewModel: NewsViewModel = hiltViewModel(),
) {
    val newsState by newsViewModel.state.collectAsState()
    val currentUser by userViewModel.currentUser.collectAsState()
    val searchPrompt by newsViewModel.searchQuery.collectAsState()

    when (val state = newsState) {
        is NewsState.Load -> {
            LoadingWidget()
        }
        is NewsState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Ошибка: ${state.e.localizedMessage}")
            }
        }
        is NewsState.NewsContent -> {
            Log.d("currentUser", "$currentUser")

            ScreenWrapper(
                topBar = {
                    currentUser?.let { user ->
                        HomeTopBar(
                            imageUrl = user.imageId,
                            searchPrompt = searchPrompt,
                            onSearchQueryChange = newsViewModel::onSearchQueryChanged
                        )
                    }
                },
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