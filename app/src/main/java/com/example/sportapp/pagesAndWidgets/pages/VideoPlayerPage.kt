package com.example.sportapp.pagesAndWidgets.pages

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.pagesAndWidgets.widgets.youtube.YoutubePlayer

@Composable
fun VideoPlayerPage ( youTubeViewModel: YoutubeActivityViewModel ){
    YoutubePlayer (youTubeViewModel.selectedVideo.id.videoId, LocalLifecycleOwner.current)
}


