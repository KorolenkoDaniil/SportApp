package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.GlobalResultInfoDto
import com.example.sportapp.support2026.features.soccer.domain.entities.GlobalResultInfo

fun GlobalResultInfoDto.toDomain(): GlobalResultInfo {
    return GlobalResultInfo(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        kind = this.kind.orEmpty()
    )
}

fun GlobalResultInfo.toDto(): GlobalResultInfoDto {
    return GlobalResultInfoDto(
        id = this.id,
        name = this.name,
        kind = this.kind
    )
}