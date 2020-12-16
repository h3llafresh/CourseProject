package com.example.courseproject.model.meal

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM Meal")
    fun selectAllMeals(): Flow<List<MealEntity>>

    @Query("SELECT * FROM Meal WHERE date = :todayDate")
    fun selectAllTodayMeals(todayDate: String): Flow<List<MealEntity>>

    @Query("SELECT * FROM Meal WHERE mealID = :mealID")
    suspend fun selectMeal(mealID: Int): MealEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMeal(meal: MealEntity)

    @Update
    suspend fun updateMeal(meal: MealEntity)

    @Delete
    suspend fun deleteMeal(meal: MealEntity)
}