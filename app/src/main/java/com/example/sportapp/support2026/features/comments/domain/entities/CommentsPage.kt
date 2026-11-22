package com.example.sportapp.support2026.features.comments.domain.entities

data class CommentsPage (
    val pageNumber: Int,
    val pageSize: Int,
    val totalItems: Int,
    val totalPages: Int,
    val itemsList: List<Comment>
)