package com.kotlin.roomdatabase.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_users")
class Users(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
            @ColumnInfo(name = "userName") var userName: String)