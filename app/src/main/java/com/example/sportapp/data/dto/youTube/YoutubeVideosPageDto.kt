package com.example.sportapp.CleanArchitexture.data.dto.youTube

import com.example.sportapp.CleanArchitexture.domain.models.youTube.SnippetEntity
import com.example.sportapp.CleanArchitexture.domain.models.youTube.VideoEntity
import com.example.sportapp.CleanArchitexture.domain.models.youTube.VideoIdEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeVideosPageDto(
    @SerialName("kind") val kind: String,
    @SerialName("etag") val etag: String,
    @SerialName("nextPageToken") val nextPageToken: String,
    @SerialName("regionCode") val regionCode: String,
    @SerialName("pageInfo") val pageInfo: PageInfo,
    @SerialName("items") val items: List<VideoDto>
) {

    fun VideoToVideoEntity(): List<VideoEntity> {
        return items.mapNotNull { item ->

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
                        thumbnailUrl =  "https://i.ytimg.com/vi/" + videoId + "/maxresdefault.jpg",
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



