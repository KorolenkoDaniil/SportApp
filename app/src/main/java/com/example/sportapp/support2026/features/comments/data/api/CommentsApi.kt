package com.example.sportapp.support2026.features.comments.data.api

import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentDtoRequest
import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentDtoResponse
import com.example.sportapp.support2026.features.comments.data.dto.comment.EditCommentRequest
import com.example.sportapp.support2026.features.comments.data.dto.commentList.CommentDtoPage

interface CommentsApi {

    suspend fun getPaginatedCommentsList(
        pageNumber: Int,
        newsId: Int,
        email: String?
    ) : CommentDtoPage

    suspend fun addNewComment (
        commentDtoRequest: CommentDtoRequest
    ) : CommentDtoResponse

    suspend fun deleteComment(
        commentId: Int
    ) : Boolean

    suspend fun editComment(
        editRequest: EditCommentRequest
    ) : Boolean


}
