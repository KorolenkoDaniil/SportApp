package com.example.sportapp.support2026.features.news.data.dto.newsDetails

import com.example.sportapp.support2026.app.baseUrl
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails
import java.time.LocalDateTime

object NewsDetailsMapper {

    fun mapNewsDetailsDtoToEntity (dto: NewsDetailsDto): NewsDetails{
        val imageUrl = if (!dto.imageId.isNullOrBlank()) {

            "${baseUrl}/images/${dto.imageId}"
        } else {
            "https://habrastorage.org/r/w1560/getpro/habr/upload_files/9c7/5fa/c54/9c75fac54ebb0beaf89abd7d86b4787c.jpg"
        }

        return NewsDetails(
            id = dto.id ?: 1,
            dateTime = LocalDateTime.parse(dto.dateTime),
            sport = dto.sport ?: "sport",
            imageId = imageUrl,
            text = dto.text ?: "текст не придумали",
            title = dto.title?: "не пришел title"
        )
    }
}