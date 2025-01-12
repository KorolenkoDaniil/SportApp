package com.example.sportapp.models.youtube.domain

data class SnippetEntity(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: ThumbnailsEntity,
    val channelTitle: String,
    val liveBroadcastContent: String,
    val publishTime: String
)
