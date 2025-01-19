
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppActivityViewModel : ViewModel() {
    private val _pageName = MutableStateFlow("Home")

    val pageName: StateFlow<String> get() = _pageName

    fun changePageName(newPageName: String) {
        _pageName.value = newPageName
    }

    private val _showBars = MutableStateFlow(false)

    val showBars: StateFlow<Boolean> get() = _showBars

    fun changeShowBars(showBars: Boolean) {
        _showBars.value = showBars
    }
}
