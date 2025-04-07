package com.example.sportapp.models.news

import com.example.sportapp.models.news.api.NewsItemResponse
import com.example.sportapp.models.news.api.NewsResponse
import com.example.sportapp.models.news.domain.NewsEntity
import com.example.sportapp.models.news.domain.NewsListEntity
import java.time.LocalDateTime

class NewsMapper {

    val tagsMapper = TagsMapper()
    val commentsMapper = CommentsMapper()

    fun getNewsEntityList(response: NewsResponse, newsBaseUrl: String): NewsListEntity {

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
                    tags = tagsMapper.tagResponseToItems(item.newsTags),
                    comments = commentsMapper.CommentsResponseToItems(
                        CommentsResponse = item.comments
                    )
                )
            }
        )
    }

    fun getOneNewsEntity(response: NewsItemResponse, newsBaseUrl: String): NewsEntity {
        return NewsEntity(
            dateTime = StringToDateTime(response.dateTime),
            sport = response.sport,
            title = response.title,
            imageId = response.imageId,
            newsImage = newsBaseUrl + "/images/" + response.imageId,
            articleText = response.articleText,
            tags = tagsMapper.tagResponseToItems(response.newsTags),
            comments = commentsMapper.CommentsResponseToItems(
                CommentsResponse = response.comments
            )
        )
    }


    fun StringToDateTime(date: String): LocalDateTime {
        return LocalDateTime.parse(date)
    }
}