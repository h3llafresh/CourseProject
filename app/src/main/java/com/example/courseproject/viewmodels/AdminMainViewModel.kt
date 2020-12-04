package com.example.courseproject.viewmodels

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.courseproject.Constants
import com.example.courseproject.Constants.APP_PREFERENCES
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import kotlinx.coroutines.launch

class AdminMainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    private val appPreferences = application.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
        refreshGuestsData()
        refreshMealsData()
        refreshServicesData()
    }

    private val _guests = repository.guests?.asLiveData()
    val guests get() = _guests!!

    private val _meals = repository.meals?.asLiveData()
    val meals get() = _meals!!

    private val _services = repository.services?.asLiveData()
    val services get() = _services!!

    private fun refreshGuestsData() {
        viewModelScope.launch {
            repository.refreshGuests()
        }
    }

    private fun refreshMealsData() {
        viewModelScope.launch {
            repository.refreshMeals()
        }
    }

    private fun refreshServicesData() {
        viewModelScope.launch {
            repository.refreshServices()
        }
    }

    fun exitFromAccount() {
        appPreferences.edit().putBoolean(Constants.AUTHORIZATION_STATE, false).apply()
        appPreferences.edit().putBoolean(Constants.IS_ADMIN, false).apply()
    }
}