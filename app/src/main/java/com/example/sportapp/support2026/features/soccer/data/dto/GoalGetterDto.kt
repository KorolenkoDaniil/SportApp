package com.example.sportapp.support2026.features.soccer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoalGetterDto(
    @SerialName("goalGetterId") val goalGetterId: Int? = null,
    @SerialName("goalGetterName") val goalGetterName: String? = null,
    @SerialName("goalCount") val goalCount: Int? = null
)