package com.example.courseproject.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {


    @Query("SELECT * FROM logins_and_passwords WHERE login = :inputLogin")
    suspend fun getUserLogin(inputLogin: String): LoginEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLogin(newLogin: LoginEntity)

    @Query("DELETE FROM logins_and_passwords")
    suspend fun deleteAllLogins()
}