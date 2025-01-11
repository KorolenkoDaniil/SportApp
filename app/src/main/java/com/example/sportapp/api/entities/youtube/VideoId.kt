package com.example.sportapp.api.entities.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoId(
    @SerialName("kind") val kind: String? = null,
    @SerialName("videoId") val videoId: String? = null
)