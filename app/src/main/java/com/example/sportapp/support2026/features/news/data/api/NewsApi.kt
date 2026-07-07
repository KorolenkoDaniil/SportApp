package com.example.sportapp.support2026.features.news.data.api

import com.example.sportapp.support2026.features.news.data.dto.NewsPageDto

interface NewsApi {
    suspend fun getPaginatedNewsList(pageNumber: Int) : NewsPageDto
//    suspend fun getOneNews() : com.example.sportapp.support2026.features.news.news.data.dto.NewsPageDto
}