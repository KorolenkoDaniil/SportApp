package com.example.sportapp.support2026.features.user.data.dto.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequestDto (
    @SerialName("email") val email: String,
)