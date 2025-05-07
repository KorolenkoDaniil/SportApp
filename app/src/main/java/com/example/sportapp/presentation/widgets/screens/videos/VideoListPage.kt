package com.example.sportapp.presentation.widgets.screens.videos

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.presentation.widgets.screens.videos.youtube.SnippetList


@Composable
fun VideoListPage(
    videoViewModel: YoutubeActivityViewModel,
    navController: NavHostController,
) {
    SnippetList(videoViewModel, navController)
}





