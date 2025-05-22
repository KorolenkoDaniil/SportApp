
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AppActivityViewModel : ViewModel() {
    private val _pageName = MutableStateFlow("Home")

    fun changePageName(newPageName: String) {
        _pageName.value = newPageName
    }


    private val _appTheme = MutableStateFlow (false )

    val appTheme = _appTheme

    fun changeAppTheme () {
        _appTheme.value = !_appTheme.value
    }

    fun customChangeAppTheme (isDark: Boolean) {
        _appTheme.value = isDark
    }


    private val _aiChatPagePaddings = MutableStateFlow(false)

    fun changeAIChat(showBars: Boolean) {
        _aiChatPagePaddings.value = showBars
    }
}
