package com.example.sportapp.presentation.widgets.screens.videos

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.presentation.widgets.screens.videos.youtube.YoutubePlayer

@Composable
fun VideoPlayerPage ( youTubeViewModel: YoutubeActivityViewModel ){

    //TODO добавить лайки

    //TODO добавить комментарии

    //TODO добавить "рекомендованные" видео

    //TODO написать дату публикаии

    //TODO выделять кнопку меню

    YoutubePlayer (youTubeViewModel.selectedVideo.id.videoId, LocalLifecycleOwner.current, youTubeViewModel)

}


