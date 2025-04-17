
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppActivityViewModel : ViewModel() {
    private val _pageName = MutableStateFlow("Home")

    fun changePageName(newPageName: String) {
        _pageName.value = newPageName
    }


    private val _aiChatPagePaddings = MutableStateFlow(false)

    val AIChatPagePaddings: StateFlow<Boolean> get() = _aiChatPagePaddings

    fun changeAIChat(showBars: Boolean) {
        _aiChatPagePaddings.value = showBars
    }
}
