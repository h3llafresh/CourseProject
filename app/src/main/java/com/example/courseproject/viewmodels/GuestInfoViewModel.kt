package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.guest.GuestEntity
import kotlinx.coroutines.runBlocking

class GuestInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    private var _guest: GuestEntity? = null

    val guest get() = _guest!!

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
    }

    fun selectGuest(guestID: Int) {
        runBlocking {
            _guest = repository.selectGuest(guestID)
        }
    }

    fun deleteGuest() {
        runBlocking {
            repository.deleteGuest(guest)
        }
    }
}