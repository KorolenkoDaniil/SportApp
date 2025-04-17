package com.example.sportapp.CleanArchitexture.data.dto.youTube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    @SerialName("kind") val kind: String,
    @SerialName("etag") val etag: String,
    @SerialName("id") val id: VideoId?,
    @SerialName("snippet") val snippet: Snippet
)