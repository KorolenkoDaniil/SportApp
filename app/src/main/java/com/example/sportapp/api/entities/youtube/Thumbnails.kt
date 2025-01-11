package com.example.sportapp.api.entities.youtube

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails(
    @SerialName("default") val default: Thumbnail? = null,
    @SerialName("medium") val medium: Thumbnail? = null,
    @SerialName("high") val high: Thumbnail? = null
)

@Serializable
data class Thumbnail(
    @SerialName("url") val url: String? = null,
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null
)
