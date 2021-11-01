package com.kotlin.roomdatabase.roomDataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.roomdatabase.models.Users
import kotlinx.coroutines.launch

class UsersViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UsersRepository
    val allUsers: LiveData<List<Users>>

    init {
        val dao = UsersDataBase.getDatabase(application).usersDAO()
        repository = UsersRepository(dao)
        allUsers = getUsers()
    }

    fun insert(user: Users)=viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: Users)=viewModelScope.launch {
        repository.update(user)
    }

    fun delete(user: Users)=viewModelScope.launch {
        repository.delete(user)
    }

    private fun getUsers():LiveData<List<Users>>{
        return repository.getAllUsers()
    }

}