package yet.another.kotlin.implementation.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yet.another.kotlin.implementation.room.*

class ExtViewModel : ViewModel() {
    val user = MutableLiveData<List<User>>()

    fun ambilUser(context: Context) {
        val dataSource = DataSource(context)
        dataSource.ambilUser { result ->
            user.postValue(result)
        }
    }
}

