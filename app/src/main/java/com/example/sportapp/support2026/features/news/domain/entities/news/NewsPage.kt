package com.example.sportapp.support2026.features.news.domain.entities.news

class NewsPage (
    val pageNumber: Int,
    val pageSize: Int,
    val totalItems: Int,
    val totalPages: Int,
    val itemsList: List<News>,
)