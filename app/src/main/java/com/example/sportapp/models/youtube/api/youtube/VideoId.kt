package com.example.sportapp.models.youtube.api.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoId(
    @SerialName("kind") val kind: String,
    @SerialName("videoId") val videoId: String? = null
)
