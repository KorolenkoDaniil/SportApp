package com.example.sportapp.support2026.features.news.data.dto.mappers

import com.example.sportapp.support2026.app.baseUrl
import com.example.sportapp.support2026.features.news.domain.entities.news.News
import com.example.sportapp.support2026.features.news.data.dto.components.NewsDto
import java.time.LocalDateTime

object NewsMapper {
    fun mapNewsToEntity(dto: NewsDto): News {

        val imageUrl = if (!dto.imageId.isNullOrBlank()) {
            // Если id картинки есть, собираем путь к твоему ngrok/серверу
            "${baseUrl}/images/${dto.imageId}"
        } else {
            // Если картинки нет, отдаем дефолтную заглушку из интернета
            "https://habrastorage.org/r/w1560/getpro/habr/upload_files/9c7/5fa/c54/9c75fac54ebb0beaf89abd7d86b4787c.jpg"
        }

        return News(
            dateTime = LocalDateTime.parse(dto.dateTime),
            sport = dto.sport ?: "sport",
            imageId = imageUrl,
            text = dto.text ?: "текст не придумали",
            title = dto.title?: "не пришел title"
        )
    }

    fun mapList(dtoList: List<NewsDto>?): List<News> {
        return dtoList?.map(::mapNewsToEntity) ?: emptyList()
    }
}