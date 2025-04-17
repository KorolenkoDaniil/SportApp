package com.example.sportapp.CleanArchitexture.data.dto.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsTagDto (
    @SerialName("tag") val tag: String,
    @SerialName("newsDateTime") val newsDateTime: String,
)