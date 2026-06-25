//package com.example.sportapp.presentation.widgets.screens.videos.youtube
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.snapshotFlow
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import coil.compose.rememberAsyncImagePainter
//import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
//import com.example.sportapp.presentation.navigation.Screen
//import com.example.sportapp.ui.theme.VideoText
//import kotlinx.coroutines.flow.collectLatest
//
//@Composable
//fun SnippetList(
//    videoViewModel: YoutubeActivityViewModel,
//    navController: NavHostController
//) {
//
//    //TODO добавить падинги для текста
//
//    //TODO добавить ui лайков
//
//    //TODO написать дату публикаии
//
//    val listState = rememberLazyListState()
//
//    LaunchedEffect(Unit) {
//        if (videoViewModel.videoList.isEmpty()) {
//            videoViewModel.loading.value = true
//            videoViewModel.videoList.addAll(videoViewModel.loadVideos())
//            videoViewModel.loading.value = false
//        }
//    }
//
//    LaunchedEffect(listState) {
//        if (videoViewModel.page.value > 0)
//            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
//                .collectLatest { index ->
//                    if (!videoViewModel.loading.value && index != null && index >= videoViewModel.videoList.size - 5) {
//                        videoViewModel.page.value++
//                        videoViewModel.loading.value = true
//                        videoViewModel.videoList.addAll(videoViewModel.loadVideos())
//                        videoViewModel.loading.value = false
//                    }
//                }
//    }
//
//
//    LazyColumn(state = listState, modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
//        items(videoViewModel.videoList.size) { index ->
//            val video = videoViewModel.videoList[index]
//            val thumbnail = rememberAsyncImagePainter(video.snippet.thumbnailUrl)
//
//            Image(
//                painter = thumbnail,
//                contentDescription = "videoImage",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .clickable {
//                        navController.navigate(Screen.VideoPlayerPage.route)
//                        videoViewModel.selectedVideo = video
//                    },
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(Modifier.height(4.dp))
//            Text(text = video.snippet.title, style = MaterialTheme.typography.VideoText)
//            Spacer(Modifier.height(16.dp))
//        }
//
//        item {
//            if (videoViewModel.loading.value) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(10.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator(modifier = Modifier.height(50.dp))
//                }
//            }
//        }
//    }
//}