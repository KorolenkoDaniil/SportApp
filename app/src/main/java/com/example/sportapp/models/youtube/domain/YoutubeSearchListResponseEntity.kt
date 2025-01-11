package com.example.sportapp.models.youtube.domain

import com.example.sportapp.models.youtube.api.entities.youtube.PageInfo
import com.example.sportapp.models.youtube.api.entities.youtube.Video


data class YoutubeSearchListResponseEntity(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val regionCode: String,
    val pageInfo: PageInfo,
    val items: List<Video>
)

