package com.example.sportapp.CleanArchitexture.data.mappers

import com.example.sportapp.CleanArchitexture.data.dto.comments.CommentDto
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity

class CommentsMapper {

    val userMapper = UserMapper()

    fun convertToListEntity(response: List<CommentDto>): List<CommentEntity> {

        return response.map { item ->
            CommentEntity(
                newsDateTime = item.newsDateTime,
                commentDateTime = item.commentDateTime,
                commentText = item.commentText,
                email = item.email,
                elapsedTime = item.ElapsedTime,
                isLiked = item.isLiked,
                likesCount = item.LikesCount,
                commentId = item.commentId,
                user = userMapper.UserResponseToEntity(item.user),
            )
        }
    }

    fun convertToEntity(response: CommentDto): CommentEntity {

        return CommentEntity(
            newsDateTime = response.newsDateTime,
            commentDateTime = response.commentDateTime,
            commentText = response.commentText,
            email = response.email,
            elapsedTime = response.ElapsedTime,
            isLiked = response.isLiked,
            likesCount = response.LikesCount,
            commentId = response.commentId,
            user = userMapper.UserResponseToEntity(response.user)
        )
    }


    fun convertToDto(response: CommentEntity): CommentDto {

        return CommentDto(
            newsDateTime = response.newsDateTime,
            commentDateTime = response.commentDateTime,
            commentText = response.commentText,
            email = response.email,
            ElapsedTime = response.elapsedTime,
            isLiked = response.isLiked,
            commentId = response.commentId,
            user = userMapper.ConvertToDto(response.user),
            LikesCount = response.likesCount,
        )

    }
}