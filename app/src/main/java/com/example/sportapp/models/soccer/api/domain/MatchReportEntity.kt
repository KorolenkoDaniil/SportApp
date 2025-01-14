package com.example.sportapp.models.soccer.api.domain


data class MatchReportEntity(
    val pageIndex: Int,
    val pageSize: Int,
    val itemsCount: Int,
    val isLastPage: Boolean,
    val items: List<EventEntity>
)
