package com.example.sportapp.support2026.features.news.domain.entities.news

import java.time.LocalDateTime


data class NewsDetails (
    val id: Int,
    val dateTime: LocalDateTime,
    val sport: String,
    val imageId: String,
    val text: String,
    val title: String,
){
    override fun toString(): String {
        return "NewsDetails(id=$id, title='$title', sport='$sport', dateTime=$dateTime, text=$text)"
    }
}
