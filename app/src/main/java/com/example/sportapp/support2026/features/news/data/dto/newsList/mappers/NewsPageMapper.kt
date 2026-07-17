package com.example.sportapp.support2026.features.news.data.dto.newsList.mappers

import com.example.sportapp.support2026.features.news.data.dto.newsList.NewsPageDto
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsPage

object NewsPageMapper {

    fun mapNewsPageToEntity(dto: NewsPageDto): NewsPage {
        return NewsPage(
            pageNumber = dto.pageNumber,
            pageSize = dto.pageSize,
            totalItems = dto.totalItems,
            totalPages = dto.totalPages,
            itemsList = NewsMapper.mapList(dto.itemsList)
        )
    }
}
