package com.example.sportapp.CleanArchitexture.data.mappers

import com.example.sportapp.CleanArchitexture.data.dto.news.NewsDto
import com.example.sportapp.CleanArchitexture.data.dto.news.NewsPageDto
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsListEntity
import java.time.LocalDateTime

class NewsMapper {

    val tagsMapper = TagsMapper()


    fun getNewsEntityList(response: NewsPageDto, newsBaseUrl: String): NewsListEntity {

        return NewsListEntity(
            pageNumber = response.pageNumber,
            pageSize = response.pageSize,
            totalItems = response.totalItems,
            totalPages = response.totalPages,
            news = response.news.map { item ->
                NewsEntity(
                    dateTime = StringToDateTime(item.dateTime),
                    sport = item.sport,
                    title = item.title,
                    imageId = item.imageId,
                    newsImage = newsBaseUrl + "/images/" + item.imageId,
                    articleText = item.articleText,
                    commentsCount = item.commentsCount,
                    likesCount = item.likesCount,
                    isLiked = item.isLiked,
                    tags = tagsMapper.tagResponseToItems(item.newsTags)
                )
            }
        )
    }

    fun getOneNewsEntity(response: NewsDto, newsBaseUrl: String): NewsEntity {
        return NewsEntity(
            dateTime = StringToDateTime(response.dateTime),
            sport = response.sport,
            title = response.title,
            imageId = response.imageId,
            newsImage = newsBaseUrl + "/images/" + response.imageId,
            articleText = response.articleText,
            commentsCount = response.commentsCount,
            likesCount = response.likesCount,
            isLiked = response.isLiked,
            tags = tagsMapper.tagResponseToItems(response.newsTags),
        )
    }


    fun StringToDateTime(date: String): LocalDateTime {
        return LocalDateTime.parse(date)
    }
}