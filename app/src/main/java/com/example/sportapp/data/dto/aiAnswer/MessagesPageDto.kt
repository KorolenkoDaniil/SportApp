package com.example.sportapp.data.dto.aiAnswer

import com.example.sportapp.CleanArchitexture.data.dto.aiAnswer.MessageDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessagesPageDto (
    @SerialName("pageNumber") val pageNumber: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("totalItems") val totalItems: Int,
    @SerialName("totalPages") val totalPages: Int,
    @SerialName("messages") val messages: List<MessageDto>,
)