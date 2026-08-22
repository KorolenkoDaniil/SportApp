package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.ResultInfoDto
import com.example.sportapp.support2026.features.soccer.domain.entities.ResultInfo

fun ResultInfoDto.toDomain(): ResultInfo {
    return ResultInfo(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        description = this.description.orEmpty(),
        orderId = this.orderId ?: 0,
        globalResultInfo = this.globalResultInfo?.toDomain()
    )
}


fun ResultInfo.toDto(): ResultInfoDto {
    return ResultInfoDto(
        id = this.id,
        name = this.name,
        description = this.description,
        orderId = this.orderId,
        globalResultInfo = this.globalResultInfo?.toDto()
    )
}