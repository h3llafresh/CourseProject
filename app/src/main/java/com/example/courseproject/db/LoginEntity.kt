package com.example.courseproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logins_and_passwords")
class LoginEntity(
    @PrimaryKey(autoGenerate = true) val loginID: Int,
    @ColumnInfo val login: String,
    @ColumnInfo val password: String,
    @ColumnInfo(name = "is_admin") val isAdmin: Boolean
)