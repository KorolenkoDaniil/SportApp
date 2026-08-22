package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDto(
    @SerialName("groupName") val groupName: String? = null,
    @SerialName("groupOrderID") val groupOrderID: Int? = null,
    @SerialName("groupID") val groupID: Int? = null
)