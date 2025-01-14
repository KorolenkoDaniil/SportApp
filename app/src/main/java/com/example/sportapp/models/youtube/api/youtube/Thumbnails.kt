package com.example.sportapp.models.youtube.api.youtube

import com.example.sportapp.models.youtube.domain.ThumbnailEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails(
    @SerialName("default") val default: Thumbnail,
    @SerialName("medium") val medium: Thumbnail,
    @SerialName("high") val high: Thumbnail
) {
    fun defaultThumbnailToThumbnailEntity(): ThumbnailEntity {
        return ThumbnailEntity(
            url = this.default.url,
            width = this.default.width,
            height = this.default.height
        )
    }

    fun mediumThumbnailToThumbnailEntity(): ThumbnailEntity {
        return ThumbnailEntity(
            url = this.default.url,
            width = this.default.width,
            height = this.default.height
        )
    }

    fun highThumbnailToThumbnailEntity(): ThumbnailEntity {
        return ThumbnailEntity(
            url = this.default.url,
            width = this.default.width,
            height = this.default.height
        )
    }
}

@Serializable
data class Thumbnail(
    @SerialName("url") val url: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int
)
