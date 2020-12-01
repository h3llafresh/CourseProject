package com.example.courseproject

import com.example.courseproject.model.LoginDao
import com.example.courseproject.model.LoginEntity

//it may be a better way to pass HotelRoomDatabase and get daos. In codelab it wasn't necessary because of there was only one dao

class HotelRepository(private val loginDao: LoginDao) {

    suspend fun getUserLoginData(inputLogin: String): LoginEntity {
        return loginDao.getUserLogin(inputLogin)
    }

    suspend fun insertLogin(newLogin: LoginEntity) {
        loginDao.insertLogin(newLogin)
    }

}