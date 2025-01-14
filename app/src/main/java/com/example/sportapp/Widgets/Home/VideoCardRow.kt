package com.example.sportapp.widgets.home

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import com.example.sportapp.models.youtube.domain.VideoEntity
import com.example.sportapp.shared.VideoCard

@Composable
fun VideoCardRow (videoList: List<VideoEntity>){
    LazyRow {
        items(videoList.size) { index ->
            VideoCard(videoList[index])
        }
    }
}


