package com.example.sportapp.models.youtube.domain

import com.example.sportapp.models.youtube.api.youtube.PageInfo


data class YoutubeSearchListResponseEntity(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val regionCode: String,
    val pageInfo: PageInfo,
    val items: List<VideoEntity>
)

