package com.example.sportapp.support2026.features.comments.domain.repository

import com.example.sportapp.support2026.features.comments.data.dto.comment.EditCommentRequest
import com.example.sportapp.support2026.features.comments.domain.entities.Comment
import com.example.sportapp.support2026.features.comments.domain.entities.CommentsPage

interface CommentsDomainRepository {

    suspend fun getPaginatedCommentsList(
        pageNumber: Int,
        newsId: Int,
        email: String?
    ) : CommentsPage

    suspend fun addNewComment (
        commentText: String, newsId: Int, email: String
    ) : Comment

    suspend fun deleteComment(
        commentId: Int
    ) : Boolean

    suspend fun editComment(
        editRequest: EditCommentRequest
    ) : Boolean
}