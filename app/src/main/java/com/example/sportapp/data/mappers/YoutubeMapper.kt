package com.example.sportapp.CleanArchitexture.data.mappers

import com.example.sportapp.CleanArchitexture.data.dto.youTube.YoutubeVideosPageDto
import com.example.sportapp.CleanArchitexture.domain.models.youTube.VideoPlayListResponseEntity

class YoutubeMapper {

    fun getListVideo(response: YoutubeVideosPageDto): VideoPlayListResponseEntity {
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
