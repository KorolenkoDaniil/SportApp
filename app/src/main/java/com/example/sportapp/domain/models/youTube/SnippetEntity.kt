package com.example.sportapp.CleanArchitexture.domain.models.youTube

data class SnippetEntity(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val channelTitle: String,
    val liveBroadcastContent: String,
    val publishTime: String
)
