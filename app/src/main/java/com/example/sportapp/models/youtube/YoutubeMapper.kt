package com.example.sportapp.models.youtube

import com.example.sportapp.models.youtube.api.youtube.YoutubeSearchListResponse
import com.example.sportapp.models.youtube.domain.YoutubeSearchListResponseEntity

class YoutubeMapper {

    fun getListVideo(response: YoutubeSearchListResponse): YoutubeSearchListResponseEntity {
        return YoutubeSearchListResponseEntity(
            kind = response.kind,
            etag = response.etag,
            nextPageToken = response.nextPageToken,
            regionCode = response.regionCode,
            pageInfo = response.pageInfo,
            items = response.VideoToVideoEntity()
        )
    }
}
