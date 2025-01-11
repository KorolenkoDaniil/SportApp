package com.example.sportapp.models.youtube.domain

import com.example.sportapp.models.youtube.api.entities.youtube.Thumbnail
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
