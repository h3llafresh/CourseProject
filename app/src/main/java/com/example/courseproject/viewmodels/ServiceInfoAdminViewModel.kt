package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.service.ServiceEntity
import kotlinx.coroutines.runBlocking

class ServiceInfoAdminViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    private var _service: ServiceEntity? = null

    val service get() = _service!!

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
    }

    fun selectService(serviceID: Int) {
        runBlocking {
            _service = repository.selectService(serviceID)
        }
    }

    fun deleteService(service: ServiceEntity) {
        runBlocking {
            repository.deleteService(service)
        }
    }
}