package com.example.courseproject.model.login

import androidx.room.*
import com.example.courseproject.model.guest.GuestAndLogin

@Dao
interface LoginDao {
    @Query("SELECT * FROM logins_and_passwords WHERE login = :inputLogin")
    fun getUserLogin(inputLogin: String): LoginEntity

    @Transaction
    @Query("SELECT * FROM logins_and_passwords")
    suspend fun getLoginAndUser(): List<GuestAndLogin>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLogin(newLogin: LoginEntity)

    @Update
    suspend fun updateLogin(newLogin: LoginEntity)

    @Query("DELETE FROM logins_and_passwords")
    suspend fun deleteAllLogins()
}