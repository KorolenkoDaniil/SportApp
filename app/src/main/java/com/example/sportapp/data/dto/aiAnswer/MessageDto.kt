package com.example.sportapp.CleanArchitexture.data.dto.aiAnswer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto (
    @SerialName("user_email") val userEmail: String,
    @SerialName("message_text") val messageText: String,
    @SerialName("is_ai_answer") val isAiAnswer: Boolean,
)



