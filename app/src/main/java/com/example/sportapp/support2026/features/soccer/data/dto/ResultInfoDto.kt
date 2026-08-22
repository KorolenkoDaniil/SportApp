package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultInfoDto(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("orderId") val orderId: Int? = null,
    @SerialName("globalResultInfo") val globalResultInfo: GlobalResultInfoDto? = null
)