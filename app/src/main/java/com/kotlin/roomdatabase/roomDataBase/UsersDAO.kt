package com.kotlin.roomdatabase.roomDataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlin.roomdatabase.models.Users

@Dao
interface UsersDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: Users): Long

    @Update
    suspend fun updateUser(user: Users): Int

    @Delete
    suspend fun deleteUser(user: Users): Int

    @Query("SELECT * FROM table_users ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<Users>>

}