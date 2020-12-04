package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.guest.GuestEntity
import kotlinx.coroutines.launch

class GuestAddViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HotelRepository

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
    }

    fun addGuest(guest: GuestEntity) {
        viewModelScope.launch {
            repository.addGuest(guest)
        }
    }
}