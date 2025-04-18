package com.example.sportapp.CleanArchitexture.domain.models.comments

import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity

data class CommentEntity (
    val commentId: Int,
    val newsDateTime: String,
    val commentDateTime: String,
    val commentText: String,
    val email: String,
    val user: UserEntity,
    val elapsedTime: String,
    var isLiked: Boolean,
    val likesCount: Int
)