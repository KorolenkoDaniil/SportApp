package com.example.sportapp.models.youtube.api.youtube

import com.example.sportapp.models.youtube.domain.VideoIdEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName("kind") val kind: String,
    @SerialName("etag") val etag: String,
    @SerialName("id") val id: VideoId,
    @SerialName("snippet") val snippet: Snippet
)

fun VideoIDToVideoIdEntity(id : VideoId): VideoIdEntity {
    return VideoIdEntity(
        kind = id.kind,
        videoId = id.videoId
    )
}
