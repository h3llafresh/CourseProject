package com.example.courseproject.model.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
data class LoginEntity(
    @PrimaryKey(autoGenerate = true) val loginID: Int = 0,
    @ColumnInfo val login: String,
    @ColumnInfo val password: String,
    @ColumnInfo val isAdmin: Boolean
)