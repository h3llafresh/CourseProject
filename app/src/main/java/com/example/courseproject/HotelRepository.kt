package com.example.courseproject

import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.model.login.LoginEntity
import com.example.courseproject.model.meal.MealEntity
import kotlinx.coroutines.flow.Flow

class HotelRepository(hotelDatabase: HotelRoomDatabase) {

    private val loginDao = hotelDatabase.loginDao()
    private val guestDao = hotelDatabase.guestDao()
    private val mealDao = hotelDatabase.mealDao()

    var guests: Flow<List<GuestEntity>>? = null

    var meals: Flow<List<MealEntity>>? = null

    suspend fun getUserLoginData(inputLogin: String): LoginEntity {
        return loginDao.getUserLogin(inputLogin)
    }

    suspend fun insertLogin(newLogin: LoginEntity) {
        loginDao.insertLogin(newLogin)
    }

    suspend fun selectGuest(guestID: Int): GuestEntity {
        return guestDao.selectGuest(guestID)
    }

    suspend fun selectMeal(mealID: Int): MealEntity {
        return mealDao.selectMeal(mealID)
    }

    fun refreshGuests() {
        guests = guestDao.selectAllGuests()
    }

    fun refreshMeals() {
        meals = mealDao.selectAllMeals()
    }


    suspend fun addGuest(guest: GuestEntity) {
        guestDao.insertGuest(guest)
    }

    suspend fun addMeal(meal: MealEntity) {
        mealDao.insertMeal(meal)
    }

    suspend fun deleteGuest(guest: GuestEntity) {
        guestDao.deleteGuest(guest)
    }

    suspend fun deleteMeal(meal: MealEntity) {
        mealDao.deleteMeal(meal)
    }

    suspend fun updateGuest(guest: GuestEntity) {
        guestDao.updateGuest(guest)
    }

    suspend fun updateMeal(meal: MealEntity) {
        mealDao.updateMeal(meal)
    }
}