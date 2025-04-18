package com.example.sportapp.CleanArchitexture.data.dto.comments

import com.example.sportapp.CleanArchitexture.data.dto.user.UserDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentDto (
    @SerialName("comment_id") val commentId: Int,
    @SerialName("news_date_time") val newsDateTime: String,
    @SerialName("comment_date_time") val commentDateTime: String,
    @SerialName("comment_text") val commentText: String,
    @SerialName("email") val email: String,
    @SerialName("user") val user: UserDto,
    @SerialName("elapsed_time") val ElapsedTime: String,
    @SerialName("is_liked") val isLiked: Boolean,
    @SerialName("likes_count") val LikesCount: Int
)
