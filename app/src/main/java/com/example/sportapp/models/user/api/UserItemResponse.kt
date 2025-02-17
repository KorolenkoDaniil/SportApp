package com.example.sportapp.models.user.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItemResponse (
    @SerialName("userEmail") val email: String,
    @SerialName("userImage") val pictureId: String
)
