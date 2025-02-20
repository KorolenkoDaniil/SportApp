package com.example.sportapp.models.youtube.api.youtube

import com.example.sportapp.models.youtube.domain.SnippetEntity
import com.example.sportapp.models.youtube.domain.VideoEntity
import com.example.sportapp.models.youtube.domain.VideoIdEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeSearchListResponse(
    @SerialName("kind") val kind: String,
    @SerialName("etag") val etag: String,
    @SerialName("nextPageToken") val nextPageToken: String,
    @SerialName("regionCode") val regionCode: String,
    @SerialName("pageInfo") val pageInfo: PageInfo,
    @SerialName("items") val items: List<Video>
) {

    fun VideoToVideoEntity(): List<VideoEntity> {
        return items.mapNotNull { item ->
            // Проверяем, что id и videoId не null
            val videoId = item.id?.videoId
            if (item.id != null && videoId != null) {
                VideoEntity(
                    kind = item.kind,
                    etag = item.etag,
                    id = VideoIdEntity(
                        kind = item.id.kind,
                        videoId = videoId
                    ),
                    snippet = SnippetEntity(
                        publishedAt = item.snippet.publishedAt,
                        channelId = item.snippet.channelId,
                        title = item.snippet.title,
                        description = item.snippet.description,
                        thumbnails = item.snippet.VideoIDToVideoIdEntity(),
                        channelTitle = item.snippet.channelTitle,
                        liveBroadcastContent = item.snippet.liveBroadcastContent,
                        publishTime = item.snippet.publishTime
                    )
                )
            } else {
                null
            }
        }
    }

}



