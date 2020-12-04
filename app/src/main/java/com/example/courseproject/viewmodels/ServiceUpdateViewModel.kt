package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.service.ServiceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServiceUpdateViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
    }

    fun updateService(serviceUpdated: ServiceEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateService(serviceUpdated)
        }
    }
}