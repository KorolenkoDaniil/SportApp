package com.example.sportapp.presentation.widgets.screens.home.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.presentation.widgets.common.shared.VideoCard
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VideoCardRow(
    videoViewModel: YoutubeActivityViewModel,
    navController: NavHostController,
    horizontalPaddings: Dp
) {

    //TODO сделать проверку и убрать лищние зщапросы


    val listState = rememberLazyListState()

    LaunchedEffect(key1 = videoViewModel.page.value) {
        videoViewModel.loading.value = true
        videoViewModel.videoList.addAll(videoViewModel.loadVideos())
        videoViewModel.loading.value = false
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { index ->
                if (!videoViewModel.loading.value && index != null && index >= videoViewModel.videoList.size - 5) {
                    videoViewModel.page.value++
                }
            }
    }

    LazyRow(state = listState, modifier = Modifier.fillMaxSize().padding(start = horizontalPaddings)) {
        items(videoViewModel.videoList.size) { index ->

            val video = videoViewModel.videoList[index]

            VideoCard(
                video,
                navController = navController,
                videoViewModel = videoViewModel
            )
        }

        item {
            if (videoViewModel.loading.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}


