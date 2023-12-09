package yet.another.kotlin.implementation.room

import androidx.room.*
@Dao
interface UserDao {

    @Query ("SELECT * FROM users")
    suspend fun ambilUser() : List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun nambahUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}