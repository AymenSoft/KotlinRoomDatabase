package com.kotlin.roomdatabase.roomDataBase

import android.content.Context
import androidx.room.*
import com.kotlin.roomdatabase.models.Users
import androidx.room.Room

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UsersDataBase : RoomDatabase() {

    abstract fun usersDAO(): UsersDAO

    companion object {
        @Volatile
        private var INSTANCE: UsersDataBase? = null
        fun getDatabase(context: Context): UsersDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDataBase::class.java,
                    "usersDatabase.db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}