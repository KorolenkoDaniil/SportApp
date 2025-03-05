
import android.content.Context
import com.example.sportapp.models.chatMessage.MessageEntity
import com.example.sportapp.models.chatMessage.sqlLiteDB.MessagesDAO
import com.example.sportapp.models.preferencesManager.PreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatRepository(private val messagesDao: MessagesDAO, context: Context) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val messagesList = messagesDao.getAllMessages()
    private val sharedPrefManager = PreferencesManager(context)

    fun addMessage(message: MessageEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            messagesDao.insertMessage(message)
        }
        deleteExtraMessages(sharedPrefManager.getData())
    }

    fun deleteExtraMessages(limit: Int){
        coroutineScope.launch(Dispatchers.IO) {
            messagesDao.deleteExtraMessages(limit)
        }
    }
}