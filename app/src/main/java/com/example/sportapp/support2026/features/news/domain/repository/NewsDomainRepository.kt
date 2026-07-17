package com.example.sportapp.support2026.features.news.domain.repository

import com.example.sportapp.support2026.features.news.domain.entities.news.News
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails

interface NewsDomainRepository{
    suspend fun getNews(page: Int): List<News>
    suspend fun getNewsDetails(newsId: Int): NewsDetails
}