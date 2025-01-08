
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppActivityViewModel : ViewModel() {
    private val _pageName = MutableStateFlow("Home")
    val pageName: StateFlow<String> get() = _pageName

    fun changePageName(newPageName: String) {
        _pageName.value = newPageName
    }
}
