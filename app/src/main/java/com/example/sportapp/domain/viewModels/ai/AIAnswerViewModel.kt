package com.example.sportapp.models.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.CleanArchitexture.data.repositories.MessageRepository
import com.example.sportapp.CleanArchitexture.domain.models.aiAnswer.MessageEntity
import com.example.sportapp.domain.viewModels.ai.LoadHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AIAnswerViewModel(
    private val loadHistoryUseCase: LoadHistoryUseCase = LoadHistoryUseCase(),
) : ViewModel(), BaseViewModelInterface<AnswerState, MessageRepository> {

    override val state: MutableStateFlow<AnswerState> = MutableStateFlow(AnswerState.Load)

    override val repository = MessageRepository()

    override fun getState(): StateFlow<AnswerState> = state

    override fun loadData() {}

    val messagesList = mutableStateListOf<MessageEntity>()
    val page = mutableStateOf(0)
    val loading = mutableStateOf(false)


    fun loadAIAnswer(prompt: String, user: String) {
        viewModelScope.launch {
            try {
                val aiAnswer = repository.askIA(prompt, user)

                messagesList.add(aiAnswer)

                Log.d("tttAIAnswer", aiAnswer.messageText)

                state.value = AnswerState.AIAnswerContent(aiAnswer)

//                addMessageToLocalHistory(
//                    MessageEntity(
//                        userEmail = user,
//                        messageText = prompt,
//                        isAiAnswer = false
//                    )
//                )

            } catch (e: Throwable) {
                Log.e("tttAIAnswer", "Error loading news data: ${e.message}", e)
                state.value = AnswerState.Error(e)
            }
        }
    }


    fun addMessageToLocalHistory(messageEntity: MessageEntity) {
        messagesList.add(messageEntity)
    }


    fun loadAIChatHistory(
        email: String,
    ) {
        viewModelScope.launch {
            messagesList.addAll(
                (loadHistoryUseCase.loadHistoryUseCase(
                    pageNumber = page.value,
                    email = email,
                    repository = repository
                ).messages)
            )
        }
    }
}


sealed interface AnswerState : BaseState {
    data object Load : AnswerState
    data class Error(val e: Throwable) : AnswerState
    data class AIAnswerContent(val answer: MessageEntity) : AnswerState
}
