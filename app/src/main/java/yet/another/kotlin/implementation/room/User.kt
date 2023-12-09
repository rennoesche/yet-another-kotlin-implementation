package yet.another.kotlin.implementation.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="nama")
    val nama: String,
    @ColumnInfo(name="asal")
    val asal: String
) : Parcelable
