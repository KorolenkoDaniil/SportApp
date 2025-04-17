package com.example.sportapp.CleanArchitexture.data.dto.comments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentsPageDto (
    @SerialName("pageNumber") val pageNumber: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("totalItems") val totalItems: Int,
    @SerialName("totalPages") val totalPages: Int,
    @SerialName("comments") val comments: List<CommentDto>,
)
