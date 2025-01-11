package com.example.sportapp.api.entities.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeSearchListResponse(
    @SerialName("kind") val kind: String? = null,
    @SerialName("etag") val etag: String? = null,
    @SerialName("nextPageToken") val nextPageToken: String? = null, // Тип "String", а не "Int", так как в JSON это строка
    @SerialName("regionCode") val regionCode: String? = null,
    @SerialName("pageInfo") val pageInfo: PageInfoEntity? = null,
    @SerialName("items") val items: List<Video>? = null
)

