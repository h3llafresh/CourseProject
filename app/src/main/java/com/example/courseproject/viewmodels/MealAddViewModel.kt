package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.meal.MealEntity
import kotlinx.coroutines.launch

class MealAddViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HotelRepository

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
    }

    fun addMeal(meal: MealEntity) {
        viewModelScope.launch {
            repository.addMeal(meal)
        }
    }
}