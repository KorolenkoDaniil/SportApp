package com.example.sportapp.models.soccer.domain


data class MatchReportEntity(
    val pageIndex: Int,
    val pageSize: Int,
    val itemsCount: Int,
    val isLastPage: Boolean,
    val items: List<EventEntity>
)
