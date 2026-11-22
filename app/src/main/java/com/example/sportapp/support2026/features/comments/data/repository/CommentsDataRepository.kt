package com.example.sportapp.support2026.features.comments.data.repository

import com.example.sportapp.support2026.features.comments.data.api.CommentsApi
import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentDtoRequest
import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentDtoResponse
import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentMapper
import com.example.sportapp.support2026.features.comments.data.dto.comment.EditCommentRequest
import com.example.sportapp.support2026.features.comments.data.dto.commentList.CommentDtoPage
import com.example.sportapp.support2026.features.comments.data.dto.commentList.CommentsListMapper
import com.example.sportapp.support2026.features.comments.domain.entities.Comment
import com.example.sportapp.support2026.features.comments.domain.entities.CommentsPage
import com.example.sportapp.support2026.features.comments.domain.repository.CommentsDomainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentsDataRepository @Inject constructor(
    private val api: CommentsApi
) : CommentsDomainRepository{


    override suspend fun getPaginatedCommentsList(
        pageNumber: Int,
        newsId: Int,
        email: String?,
    ): CommentsPage {
        val dto: CommentDtoPage = api.getPaginatedCommentsList(
            pageNumber = pageNumber,
            newsId = newsId,
            email = email
        )

        val entityPage: CommentsPage = CommentsListMapper.MapCommentsListDtoToEntity(dto)
        return entityPage
    }

    override suspend fun addNewComment(commentText: String, newsId: Int, email: String): Comment {

        val requestDto = CommentDtoRequest(
            text = commentText,
            newsId = newsId,
            email = email
        )

        val responseDto: CommentDtoResponse = api.addNewComment(
            commentDtoRequest = requestDto
        )

        val entity: Comment = CommentMapper.MapCommentDtoResponseToEntity(responseDto)
        return entity
    }

    override suspend fun deleteComment(commentId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun editComment(editRequest: EditCommentRequest): Boolean {
        TODO("Not yet implemented")
    }
}