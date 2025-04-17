package com.example.sportapp.CleanArchitexture.data.dto.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NewsDto (
    @SerialName("dateTime") val dateTime: String,
    @SerialName("sport") val sport: String,
    @SerialName("title") val title: String,
    @SerialName("imageId") val imageId: String,
    @SerialName("articleText") val articleText: String,
    @SerialName("comments_count") val commentsCount: Int,
    @SerialName("likes_count") val likesCount: Int,
    @SerialName("is_Liked") val isLiked: Boolean,
    @SerialName("tags") val newsTags: List<NewsTagDto>,
)
