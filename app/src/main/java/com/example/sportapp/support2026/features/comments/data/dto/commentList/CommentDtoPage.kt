package com.example.sportapp.support2026.features.comments.data.dto.commentList

import com.example.sportapp.support2026.features.comments.data.dto.comment.CommentDtoResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CommentDtoPage (
    @SerialName("pageNumber") val pageNumber: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("totalItems") val totalItems: Int,
    @SerialName("totalPages") val totalPages: Int,
    @SerialName("itemsList") val itemsList: List<CommentDtoResponse>?,
)