package com.example.sportapp.models.youtube.api.youtube

import com.example.sportapp.models.youtube.domain.ThumbnailsEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snippet(
    @SerialName("publishedAt") val publishedAt: String,
    @SerialName("channelId") val channelId: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("thumbnails") val thumbnails: Thumbnails,
    @SerialName("channelTitle") val channelTitle: String,
    @SerialName("liveBroadcastContent") val liveBroadcastContent: String,
    @SerialName("publishTime") val publishTime: String
) {
    fun VideoIDToVideoIdEntity(): ThumbnailsEntity {
        return ThumbnailsEntity(
            default = thumbnails.defaultThumbnailToThumbnailEntity(),
            medium = thumbnails.mediumThumbnailToThumbnailEntity(),
            high = thumbnails.highThumbnailToThumbnailEntity()
        )
    }
}

