package com.example.sportapp.CleanArchitexture.domain.models.youTube


data class VideoEntity(
    val kind: String,
    val etag: String,
    val id: VideoIdEntity,
    val snippet: SnippetEntity
)