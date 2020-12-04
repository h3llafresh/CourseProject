package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.model.login.LoginEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GuestUpdateViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
    }

    fun updateGuest(guestUpdated: GuestEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGuest(guestUpdated)
        }
    }

    fun updateLogin(updatedLogin: LoginEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLogin(updatedLogin)
        }
    }
}