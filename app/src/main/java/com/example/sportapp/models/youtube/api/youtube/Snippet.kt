package com.example.sportapp.models.youtube.api.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snippet (
    @SerialName("publishedAt") val publishedAt: String,
    @SerialName("channelId") val channelId: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("channelTitle") val channelTitle: String,
    @SerialName("liveBroadcastContent") val liveBroadcastContent: String,
    @SerialName("publishTime") val publishTime: String
)

