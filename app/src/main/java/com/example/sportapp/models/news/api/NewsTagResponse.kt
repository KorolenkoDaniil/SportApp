package com.example.sportapp.models.news.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsTagResponse (
    @SerialName("tag") val tag: String,
    @SerialName("newsDateTime") val newsDateTime: String,
)