package com.neosoft.composewithcleanarch.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
     fun getAll(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(user_model: UserEntity)

    @Query("UPDATE user SET userName = :uname WHERE id = :uid")
    suspend fun updateList(uid : Int,uname : String)

    @Query("DELETE FROM user WHERE id = :userId")
    suspend fun deleteUser(userId: Int)


}