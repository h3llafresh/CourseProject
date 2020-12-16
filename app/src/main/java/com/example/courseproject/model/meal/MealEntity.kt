package com.example.courseproject.model.meal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meal")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val mealID: Int = 0,
    @ColumnInfo val name: String,
    @ColumnInfo val type: String,
    @ColumnInfo val date: String,
    @ColumnInfo val cost: Int
)