package com.example.sportapp.CleanArchitexture.data.dto.aiAnswer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AIAnswerDto (
    @SerialName("response") val answer: String,
)


