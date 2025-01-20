package com.example.sportapp.models.news.domain

data class NewsListEntity (
    val pageNumber: Int,
    val pageSize: Int,
    val totalItems: Int,
    val totalPages: Int,
    val news: List<NewsEntity>,
)