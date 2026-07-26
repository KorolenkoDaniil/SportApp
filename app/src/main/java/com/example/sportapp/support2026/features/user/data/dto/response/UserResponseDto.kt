package com.example.sportapp.support2026.features.user.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto (
    @SerialName("id") val id: Int,
    @SerialName("user_Name") val userName: String,
    @SerialName("email") val email: String,
    @SerialName("imageId") val imageId: String,
)