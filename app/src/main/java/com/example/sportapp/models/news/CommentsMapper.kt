package com.example.sportapp.models.news

import com.example.sportapp.models.news.api.internalNewsModels.NewsCommentsResponse
import com.example.sportapp.models.news.domain.internalNewsModels.NewsCommentsEntity

class CommentsMapper {

    fun CommentsResponseToItems (CommentsResponse: List<NewsCommentsResponse>): List<NewsCommentsEntity> {

        return CommentsResponse.map { response ->
            NewsCommentsEntity(
                commentId = response.commentId,
                newsDateTime = response.newsDateTime,
                commentDateTime = response.commentDateTime,
                commentText = response.commentText,
                userEmail = response.userEmail)
        }
    }
}
