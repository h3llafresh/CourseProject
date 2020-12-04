package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.meal.MealEntity
import kotlinx.coroutines.runBlocking

class MealInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    private var _meal: MealEntity? = null

    val meal get() = _meal!!

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
    }

    fun selectMeal(mealID: Int) {
        runBlocking {
            _meal = repository.selectMeal(mealID)
        }
    }

    fun deleteMeal() {
        runBlocking {
            repository.deleteMeal(meal)
        }
    }
}