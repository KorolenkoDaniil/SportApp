package com.example.sportapp.models.news.api

import com.example.sportapp.models.news.api.internalNewsModels.NewsCommentsResponse
import com.example.sportapp.models.news.api.internalNewsModels.NewsTagResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NewsItemResponse (
    @SerialName("date_time") val dateTime: String,
    @SerialName("sport") val sport: String,
    @SerialName("title") val title: String,
    @SerialName("image_id") val imageId: String,
    @SerialName("article_texts") val articleText: String,
    @SerialName("tags") val newsTags: List<NewsTagResponse>,
    @SerialName("comments") val comments: List<NewsCommentsResponse>
)
