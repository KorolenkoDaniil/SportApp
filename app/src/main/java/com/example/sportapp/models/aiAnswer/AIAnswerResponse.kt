package com.example.sportapp.models.aiAnswer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AIAnswerResponse(
    @SerialName("answer") val answer: String,
)


