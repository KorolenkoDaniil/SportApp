package com.example.sportapp.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.pages.widgets.SnippetList


@Composable
fun VideoListPage(
    videoViewModel: YoutubeActivityViewModel,
    navController: NavHostController
) {
    val videosState by videoViewModel.state.collectAsState()

    when (videosState) {
        is VideosState.Load -> {
            Text(text = "Loading...")
        }

        is VideosState.Error -> {
            val error = (videosState as VideosState.Error).e
            Text(text = "Error: ${error.localizedMessage}")
        }

        is VideosState.VideosContent -> {
            SnippetList(videoViewModel, navController)
        }
    }
}





