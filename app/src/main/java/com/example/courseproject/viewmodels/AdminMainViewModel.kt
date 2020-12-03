package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.guest.GuestEntity
import kotlinx.coroutines.launch

class AdminMainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
        refreshGuestsData()
    }

    private val _guests = repository.guests?.asLiveData()

    val guests get() = _guests!!

    private fun refreshGuestsData() {
        viewModelScope.launch {
            repository.refreshGuests()
        }
    }

    fun addGuest(guest: GuestEntity) {
        viewModelScope.launch {
            repository.addGuest(guest)
        }
    }
}