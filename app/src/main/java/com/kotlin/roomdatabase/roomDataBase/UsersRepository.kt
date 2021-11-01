package com.kotlin.roomdatabase.roomDataBase

import androidx.lifecycle.LiveData
import com.kotlin.roomdatabase.models.Users

class UsersRepository(private val usersDAO: UsersDAO) {

    suspend fun insert(user: Users){
        usersDAO.insertUser(user)
    }

    suspend fun update(user: Users){
        usersDAO.updateUser(user)
    }

    suspend fun delete(user: Users){
        usersDAO.deleteUser(user)
    }

    fun getAllUsers():LiveData<List<Users>>{
        return usersDAO.getAllUsers()
    }

}