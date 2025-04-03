package com.example.sportapp.pagesAndWidgets.widgets.home

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.viewModels.YoutubeActivityViewModel
import com.example.sportapp.models.youtube.domain.VideoEntity
import com.example.sportapp.pagesAndWidgets.widgets.shared.VideoCard
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VideoCardRow(
    videoViewModel: YoutubeActivityViewModel,
    navController: NavHostController,
    horizontalPaddings: Dp
) {

    val page = remember { mutableStateOf(1) }
    val loading = remember { mutableStateOf(false) }
    val itemList = remember { mutableStateListOf<VideoEntity>() }
    val listState = rememberLazyListState()

    LaunchedEffect(key1 = page.value) {
        loading.value = true
        itemList.addAll(videoViewModel.loadVideos())
        loading.value = false
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { index ->
                if (!loading.value && index != null && index >= itemList.size - 5) {
                    page.value++
                }
            }
    }

    LazyRow(state = listState, modifier = Modifier.fillMaxSize().padding(start = horizontalPaddings)) {
        items(itemList.size) { index ->

            val video = itemList[index]

            VideoCard(
                video,
                navController = navController,
                videoViewModel = videoViewModel
            )
        }

        item {
            if (loading.value) {
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


