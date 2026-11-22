package com.example.sportapp.support2026.features.comments.domain.useCases

import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentDtoRequest
import com.example.sportapp.support2026.features.comments.domain.entities.Comment
import com.example.sportapp.support2026.features.comments.domain.repository.CommentsDomainRepository
import javax.inject.Inject

class AddNewCommentUseCase @Inject constructor(
    private val repository: CommentsDomainRepository) {

    suspend operator fun invoke (commentText: String, newsId: Int, email: String) : Comment {

        val dto = CommentDtoRequest(
            text = commentText,
            newsId = newsId,
            email = email
        )

        val commentEntity: Comment = repository.addNewComment(
            commentText, newsId, email
        )

        return commentEntity
    }
}