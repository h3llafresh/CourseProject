package com.example.courseproject

import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.model.login.LoginEntity
import kotlinx.coroutines.flow.Flow

//it may be a better way to pass HotelRoomDatabase and get daos. In codelab it wasn't necessary because of there was only one dao

class HotelRepository(private val hotelDatabase: HotelRoomDatabase) {

    private val loginDao = hotelDatabase.loginDao()
    private val guestDao = hotelDatabase.guestDao()

    var guests: Flow<List<GuestEntity>>? = null

    suspend fun getUserLoginData(inputLogin: String): LoginEntity {
        return loginDao.getUserLogin(inputLogin)
    }

    suspend fun insertLogin(newLogin: LoginEntity) {
        loginDao.insertLogin(newLogin)
    }

    fun refreshGuests() {
        guests = guestDao.selectAllGuests()
    }

    suspend fun addGuest(guest: GuestEntity) {
        guestDao.insertGuest(guest)
    }

}