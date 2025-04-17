package com.example.sportapp.CleanArchitexture.domain.models.comments

data class CommentsPageEntity (
    val pageNumber: Int,
    val pageSize: Int,
    val totalItems: Int,
    val totalPages: Int,
    val comments: List<CommentEntity>,
)