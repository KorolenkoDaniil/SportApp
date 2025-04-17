package com.example.sportapp.CleanArchitexture.data.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    @SerialName("email") val email: String,
    @SerialName("pictureId") val pictureId: String
)
