package com.example.sportapp.CleanArchitexture.domain.models.youTube

import com.example.sportapp.CleanArchitexture.data.dto.youTube.PageInfo


data class VideoPlayListResponseEntity(
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val regionCode: String,
    val pageInfo: PageInfo,
    val items: List<VideoEntity>
)

