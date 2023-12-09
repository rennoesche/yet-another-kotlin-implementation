package yet.another.kotlin.implementation.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import yet.another.kotlin.implementation.room.DataSource
import yet.another.kotlin.implementation.room.User

class ExtDialogViewModel : ViewModel() {
    fun nambahUser(context: Context, nama:String, asal:String, onSuccess:(Boolean) -> Unit) {
        val localDataSource = DataSource(context)
        localDataSource.nambahUser(User(0, nama, asal))
        onSuccess(true)
    }

    fun updateUser(context: Context, id:Int, nama:String, kelas:String, onSuccess: (Boolean) -> Unit) {
        val localDataSource = DataSource(context)
        localDataSource.nambahUser(User(id, nama, kelas))
        onSuccess(true)
    }

    fun deleteUser(context: Context, data:User, onSuccess: (Boolean) -> Unit) {
        val localDataSource = DataSource(context)
        localDataSource.deleteUser(data)
        onSuccess(true)
    }

}