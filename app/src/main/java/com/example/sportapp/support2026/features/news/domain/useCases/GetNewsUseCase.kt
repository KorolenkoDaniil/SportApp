package com.example.sportapp.support2026.features.news.domain.useCases

import com.example.sportapp.support2026.features.news.domain.entities.news.News
import com.example.sportapp.support2026.features.news.domain.repository.NewsDomainRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsDomainRepository
) {
    suspend operator fun invoke(page: Int): List<News> {
        return repository.getNews(page)
    }
}