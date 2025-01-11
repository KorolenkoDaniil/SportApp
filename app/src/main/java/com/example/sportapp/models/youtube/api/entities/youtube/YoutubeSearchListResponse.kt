package com.example.sportapp.models.youtube.api.entities.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeSearchListResponse(
    @SerialName("kind") val kind: String? = null,
    @SerialName("etag") val etag: String? = null,
    @SerialName("nextPageToken") val nextPageToken: String? = null,
    @SerialName("regionCode") val regionCode: String? = null,
    @SerialName("pageInfo") val pageInfo: PageInfo? = null,
    @SerialName("items") val items: List<Video>? = null
)

