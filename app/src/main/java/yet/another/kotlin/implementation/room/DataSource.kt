package yet.another.kotlin.implementation.room

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class DataSource(context: Context) {
    private val appDatabase = AppDatabase.getDatabase(context)
    private val userDao = appDatabase.UserDao()

    fun ambilUser(callback:(List<User>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            callback(userDao.ambilUser())
        }
    }

    fun nambahUser(data:User) {
        CoroutineScope(Dispatchers.Main).launch {
            userDao.nambahUser(data)
        }
    }

    fun updateUser(data:User) {
        CoroutineScope(Dispatchers.Main).launch {
            userDao.updateUser(data)
        }
    }

    fun deleteUser(data:User) {
        CoroutineScope(Dispatchers.Main).launch {
            userDao.deleteUser(data)
        }
    }

}