package com.example.courseproject.model.login

import androidx.room.*

@Dao
interface LoginDao {
    @Query("SELECT * FROM Login WHERE login = :inputLogin")
    fun getUserLogin(inputLogin: String): LoginEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLogin(newLogin: LoginEntity)

    @Update
    suspend fun updateLogin(newLogin: LoginEntity)
}