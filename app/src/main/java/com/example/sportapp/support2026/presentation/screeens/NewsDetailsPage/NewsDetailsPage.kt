package com.example.sportapp.support2026.presentation.screeens.NewsDetailsPage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sportapp.support2026.features.news.presentation.NewsDetailsState
import com.example.sportapp.support2026.features.news.presentation.NewsDetailsViewModel
import com.example.sportapp.support2026.presentation.screeens.NewsDetailsPage.widgets.TopBar
import com.example.sportapp.support2026.presentation.screeens.ScreenWrapper

@Composable
fun NewsDetailsPage (
    navController: NavHostController,
    newsId: Int,
    newsDetailsViewModel: NewsDetailsViewModel = hiltViewModel()
){

    val newsDetailsState by newsDetailsViewModel.state.collectAsState()
    val newsDetailsLoadingState by newsDetailsViewModel.loading.collectAsState()
    val loadedNewsDetails by newsDetailsViewModel.loadedNewsDetails.collectAsState()

    val newsDetails = when (newsDetailsState) {
        is NewsDetailsState.NewsDetailsContent -> (newsDetailsState as NewsDetailsState.NewsDetailsContent).newsDetails
        else -> loadedNewsDetails
    }

    LaunchedEffect(Unit) {
        if (!newsDetailsLoadingState) {
            newsDetailsViewModel.loadDetails(newsId)
        }
    }


    ScreenWrapper(
        topBar = {
            TopBar(navController)
        },
        content = {
            Text(text = "OneNewsPage $newsDetails")
        }
    )
}