package com.example.sportapp.api.entities.youtube


data class YoutubeSearchListResponseEntity(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val regionCode: String,
    val pageInfo: PageInfoEntity,
    val items: List<Video>
)

