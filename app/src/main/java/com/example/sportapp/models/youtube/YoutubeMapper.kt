package com.example.sportapp.models.youtube

import com.example.sportapp.models.youtube.api.youtube.YoutubeSearchListResponse
import com.example.sportapp.models.youtube.domain.VideoPlayListResponseEntity

class YoutubeMapper {

    fun getListVideo(response: YoutubeSearchListResponse): VideoPlayListResponseEntity {
        return VideoPlayListResponseEntity(
            kind = response.kind,
            etag = response.etag,
            nextPageToken = response.nextPageToken,
            regionCode = response.regionCode,
            pageInfo = response.pageInfo,
            items = response.VideoToVideoEntity()
        )
    }
}
