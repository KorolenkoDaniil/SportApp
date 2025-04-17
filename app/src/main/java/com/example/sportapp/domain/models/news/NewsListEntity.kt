package com.example.sportapp.CleanArchitexture.domain.models.news

data class NewsListEntity (
    val pageNumber: Int,
    val pageSize: Int,
    val totalItems: Int,
    val totalPages: Int,
    val news: List<NewsEntity>,
)