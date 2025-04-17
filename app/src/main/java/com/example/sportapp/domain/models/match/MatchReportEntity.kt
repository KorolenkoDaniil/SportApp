package com.example.sportapp.CleanArchitexture.domain.models.match


data class MatchReportEntity(
    val pageIndex: Int,
    val pageSize: Int,
    val itemsCount: Int,
    val isLastPage: Boolean,
    val items: List<EventEntity>
)
