package com.example.sportapp.support2026.features.comments.domain.useCases

import com.example.sportapp.support2026.features.comments.domain.entities.CommentsPage
import com.example.sportapp.support2026.features.comments.domain.repository.CommentsDomainRepository
import javax.inject.Inject

class GetPaginatedCommentsListUseCase @Inject constructor(
    private val repository: CommentsDomainRepository) {

    suspend operator fun invoke (
        pageNumber: Int,
        newsId: Int,
        email: String?
    ) : CommentsPage{
        return repository.getPaginatedCommentsList(
            pageNumber = pageNumber,
            newsId = newsId,
            email = email
        )
    }
}