package com.example.sportapp.support2026.features.news.domain.repository

import com.example.sportapp.support2026.features.news.domain.entities.news.News

interface NewsDomainRepository{
    suspend fun getNews(page: Int): List<News>
}