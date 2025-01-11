package com.example.sportapp.models.youtube.api.entities.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snippet(
    @SerialName("publishedAt") val publishedAt: String? = null,
    @SerialName("channelId") val channelId: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("thumbnails") val thumbnails: Thumbnails? = null,
    @SerialName("channelTitle") val channelTitle: String? = null,
    @SerialName("liveBroadcastContent") val liveBroadcastContent: String? = null,
    @SerialName("publishTime") val publishTime: String? = null
)
