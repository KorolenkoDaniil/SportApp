package com.example.sportapp.domain


data class MatchReportEntity(
    val pageIndex: Int,
    val pageSize: Int,
    val itemsCount: Int,
    val isLastPage: Boolean,
    val items: List<EventEntity>
)
