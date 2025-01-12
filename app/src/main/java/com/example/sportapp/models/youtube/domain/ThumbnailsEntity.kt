package com.example.sportapp.models.youtube.domain

import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailsEntity(
    val default: ThumbnailEntity,
    val medium: ThumbnailEntity,
    val high: ThumbnailEntity
)

@Serializable
data class ThumbnailEntity(
    val url: String,
    val width: Int,
    val height: Int
)
