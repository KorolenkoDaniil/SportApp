package com.example.sportapp.api.entities.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName("kind") val kind: String? = null,
    @SerialName("etag") val etag: String? = null,
    @SerialName("id") val id: VideoId? = null, // Важно выделить структуру id в отдельный класс
    @SerialName("snippet") val snippet: SnippetEntity? = null
)