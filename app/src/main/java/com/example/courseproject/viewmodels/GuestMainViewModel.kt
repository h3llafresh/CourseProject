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
import com.example.courseproject.model.guest.GuestEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GuestMainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    private val appPreferences = application.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
        refreshMealsData()
        refreshServicesData()
    }

    private var _guest: GuestEntity? = null
    val guest get() = _guest!!

    private val _meals = repository.meals?.asLiveData()
    val meals get() = _meals!!

    private val _services = repository.services?.asLiveData()
    val services get() = _services!!

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

    fun selectGuest(guestID: Int) {
        runBlocking {
            _guest = repository.selectGuest(guestID)
        }
    }

    fun exitFromAccount() {
        appPreferences.edit().putInt(Constants.USER_ID, 0).apply()
        appPreferences.edit().putBoolean(Constants.AUTHORIZATION_STATE, false).apply()
    }
}