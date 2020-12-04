package com.example.courseproject.model.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logins_and_passwords")
data class LoginEntity(
    @PrimaryKey(autoGenerate = true) val loginID: Int = 0,
    @ColumnInfo val login: String,
    @ColumnInfo val password: String,
    @ColumnInfo(name = "is_admin") val isAdmin: Boolean
)