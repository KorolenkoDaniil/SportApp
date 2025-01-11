package com.example.sportapp.api.entities.youtube

import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailsEntity(
    val default: Thumbnail,
    val medium: Thumbnail,
    val high: Thumbnail
)

@Serializable
data class ThumbnailEntity(
    val url: String,
    val width: Int,
    val height: Int
)
