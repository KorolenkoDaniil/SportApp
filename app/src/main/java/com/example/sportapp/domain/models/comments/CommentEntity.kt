package com.example.sportapp.CleanArchitexture.domain.models.comments

import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity

data class CommentEntity (
    val newsDateTime: String,
    val commentDateTime: String,
    val commentText: String,
    val email: String,
    val user: UserEntity,
    val elapsedTime: String,
    val isLiked: Boolean
)