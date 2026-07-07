//package com.example.sportapp.presentation.widgets.screens.videos.youtube
//
//import android.content.res.Configuration
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.lifecycle.LifecycleOwner
//import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
//
//@Composable
//fun YoutubePlayer(
//    videoId: String,
//    lifecycleOwner: LifecycleOwner,
//    youTubeViewModel: YoutubeActivityViewModel
//) {
//
//    val configuration = LocalConfiguration.current
//
//    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
//
//    if (youTubeViewModel.lastVideoId.value != videoId){
//        youTubeViewModel.currentTime.value = 0f
//        youTubeViewModel.lastVideoId.value = videoId
//    }
//
//    val modifier = if (isPortrait) {
//        Modifier.fillMaxWidth()
//            .padding(8.dp)
//            .clip(RoundedCornerShape(16.dp))
//    } else {
//        Modifier
//            .fillMaxSize()
//            .padding(0.dp)
//    }
//
//    AndroidView(
//        modifier = modifier,
//        factory = { context ->
//            YouTubePlayerView(context).apply {
//                lifecycleOwner.lifecycle.addObserver(this)
//
//                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//                    override fun onReady(youTubePlayer: YouTubePlayer) {
//                        youTubePlayer.loadVideo(videoId,  youTubeViewModel.currentTime.value)
//                    }
//
//                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
//                        youTubeViewModel.currentTime.value = second
//                    }
//                })
//            }
//        }
//    )
//}