package com.example.sportapp.support2026.features.comments.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.models.viewModels.BaseState
import com.example.sportapp.support2026.features.comments.domain.entities.Comment
import com.example.sportapp.support2026.features.comments.domain.useCases.AddNewCommentUseCase
import com.example.sportapp.support2026.features.comments.domain.useCases.DeleteCommentUseCase
import com.example.sportapp.support2026.features.comments.domain.useCases.GetPaginatedCommentsListUseCase
import com.example.sportapp.support2026.features.comments.domain.useCases.UpdateCommentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Внутри CommentsViewModel:
sealed interface CommentsState : BaseState {
    data object Load : CommentsState
    data class Error(val e: Throwable) : CommentsState
    data class CommentsContent(val comments: List<Comment>) : CommentsState
//    object используется для состояний, у которых нет данных.
//    data class используется для состояний, которые несут данные.
}


@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val addNewCommentUseCase: AddNewCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val getPaginatedCommentsListUseCase: GetPaginatedCommentsListUseCase,
    private val updateCommentUseCase: UpdateCommentUseCase
) : ViewModel() {

    private val _commentsState = MutableStateFlow<CommentsState>(CommentsState.Load)
    val state: StateFlow<CommentsState> = _commentsState.asStateFlow()

    private val _allLoadedComments = MutableStateFlow<List<Comment>>(emptyList())
    val allLoadedComments: StateFlow<List<Comment>> = _allLoadedComments.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private var currentPage = 1

    private var lastClickTime = 0L

    private val _hasMorePages = MutableStateFlow(true)
    val hasMorePages: StateFlow<Boolean> = _hasMorePages.asStateFlow()

    fun setNewLoadingState(loading: Boolean) {
        _loading.value = loading
    }

    fun CountItems(): Int {
        return _allLoadedComments.value.count()
    }

    private val _toastMessage = Channel<String>(Channel.BUFFERED)

    private val _isSending = MutableStateFlow(false)
    val isSending: StateFlow<Boolean> = _isSending.asStateFlow()

    fun loadComments(
        newsId: Int,
        email: String?,
        isNextPage: Boolean = false
    ) {
        viewModelScope.launch {
            if (_loading.value) return@launch
            if (isNextPage && !_hasMorePages.value) return@launch

            val targetPage = if (isNextPage) currentPage + 1 else 1

            try {
                _loading.value = true
                if (!isNextPage) {
                    _commentsState.value = CommentsState.Load
                    _hasMorePages.value = true
                }

                val commentsPage = getPaginatedCommentsListUseCase(
                    pageNumber = targetPage,
                    newsId = newsId,
                    email = email
                )

                val newComments = commentsPage.itemsList
                _hasMorePages.value = newComments.isNotEmpty() && commentsPage.pageNumber < commentsPage.totalPages

                newComments.forEach { comment ->
                    Log.d(
                        "tttComments",
                        "${comment.commentId} | ${comment.commentText} | ${comment.commentDateTime} | ${comment.email}"
                    )
                }

                currentPage = targetPage
                _allLoadedComments.value = if (isNextPage) {
                    (_allLoadedComments.value + newComments).distinctBy { it.commentId }
                } else {
                    newComments.distinctBy { it.commentId }
                }

                _commentsState.value = CommentsState.CommentsContent(_allLoadedComments.value)
            } catch (e: Throwable) {
                Log.d("tttComments", e.message.toString())
                if (isNextPage) {
                    _hasMorePages.value = false
                    if (_allLoadedComments.value.isNotEmpty()) {
                        _commentsState.value = CommentsState.CommentsContent(_allLoadedComments.value)
                    }
                } else {
                    _commentsState.value = CommentsState.Error(e)
                }
            } finally {
                _loading.value = false
            }
        }
    }

    fun AddNewComment(
        commentText: String,
        newsId: Int,
        email: String
    ) {
        val trimmedText = commentText.trim()
        if (trimmedText.isBlank()) {
            _toastMessage.trySend("Комментарий не может быть пустым")
            return
        }

        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < 1500) return
        lastClickTime = currentTime

        viewModelScope.launch {
            if (_isSending.value) return@launch

            try {
                _isSending.value = true

                val comment = addNewCommentUseCase(
                    commentText = trimmedText,
                    newsId = newsId,
                    email = email
                )

                Log.d(
                    "tttComments",
                    "${comment.commentId} | ${comment.commentText} | ${comment.commentDateTime} | ${comment.email}"
                )

                _allLoadedComments.value = (listOf(comment) + _allLoadedComments.value)
                    .distinctBy { it.commentId }
                _commentsState.value = CommentsState.CommentsContent(_allLoadedComments.value)
            } catch (e: Throwable) {
                _toastMessage.trySend("Не удалось отправить комментарий")
                Log.d("tttComment", e.message.toString())
            } finally {
                _isSending.value = false
            }
        }
    }
}


