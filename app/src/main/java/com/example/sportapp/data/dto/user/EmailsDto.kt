package com.example.sportapp.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class EmailsDto (
    val oldEmail: String,
    val newEmail: String
)