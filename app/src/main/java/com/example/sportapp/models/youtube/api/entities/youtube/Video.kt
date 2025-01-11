package com.example.sportapp.models.youtube.api.entities.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName("kind") val kind: String? = null,
    @SerialName("etag") val etag: String? = null,
    @SerialName("id") val id: VideoId? = null,
    @SerialName("snippet") val snippet: Snippet? = null
)