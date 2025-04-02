package com.example.sportapp.pages

import AppActivityViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.Screen
import com.example.sportapp.models.viewModels.VideosState
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel


@Composable
fun VideoListPage (
    appActivity: AppActivityViewModel,
    topPaddings: Dp,
    horizontalPaddings: Dp,
    videoViewModel: YoutubeActivityViewModel,
    navController: NavHostController
) {

    val videosState by videoViewModel.state.collectAsState()


    when (videosState) {
        is VideosState.Load -> {
            Text(text = "Loading...")
        }
        is VideosState.Error -> {
            // Обработка ошибки
            val error = (videosState as VideosState.Error).e
            Text(text = "Error: ${error.localizedMessage}")
        }
        is VideosState.VideosContent -> {

            val videos = (videosState as VideosState.VideosContent).videos.items

            LazyColumn {
                items(videos.size) { index ->
                    val video = videos[index]
                    val thumbnail = rememberAsyncImagePainter(video.snippet.thumbnailUrl)

                    Image(
                        painter = thumbnail,
                        contentDescription = "videoImage",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable {
                                navController.navigate(Screen.VideoPlayerPage.route)
                                videoViewModel.selectedVideo = video
                            },
                        contentScale = ContentScale.Crop

                    )

                    Spacer(Modifier.height(4.dp))

                    Text(text =  video.snippet.title)

                    Spacer(Modifier.height(16.dp))


                }
            }
        }
    }
}




