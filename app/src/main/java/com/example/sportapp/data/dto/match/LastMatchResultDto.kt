package com.example.sportapp.CleanArchitexture.data.dto.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastMatchResultDto (
    @SerialName("result") val result: String
)