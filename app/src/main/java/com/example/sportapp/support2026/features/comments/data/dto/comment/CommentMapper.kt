package com.example.sportapp.support2026.features.comments.data.dto.comment

import com.example.sportapp.support2026.features.comments.domain.entities.Comment
import java.time.LocalDateTime

object CommentMapper {

    fun MapEntityToCommentDtoRequest (entity: Comment ): CommentDtoRequest{

        return CommentDtoRequest(
            text = entity.commentText,
            newsId = entity.newsId,
            email = entity.email
        )
    }


    fun MapCommentDtoResponseToEntity (dto: CommentDtoResponse):Comment{
        return Comment(
            commentId = dto.commentId,
            commentText = dto.commentText,
            newsId = dto.newsId,
            email = dto.email,
            commentDateTime = LocalDateTime.parse(dto.commentDateTime)
        )
    }

    fun MapEntityToEditCommentRequest(entity: Comment): EditCommentRequest{

        return EditCommentRequest(
            commentId = entity.commentId,
            newsId = entity.newsId,
            email = entity.email)
    }


    fun mapList(dtoList: List<CommentDtoResponse>?): List<Comment> {
        return dtoList?.map(::MapCommentDtoResponseToEntity) ?: emptyList()
    }
}
