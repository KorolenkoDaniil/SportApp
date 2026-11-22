package com.example.sportapp.support2026.features.comments.domain.useCases

import com.example.sportapp.support2026.features.comments.domain.repository.CommentsDomainRepository
import javax.inject.Inject

class DeleteCommentUseCase @Inject constructor(
    private val repository: CommentsDomainRepository) {

    suspend operator fun invoke (){

    }
}