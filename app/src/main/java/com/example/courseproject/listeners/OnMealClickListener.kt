package com.example.courseproject.listeners

import com.example.courseproject.model.meal.MealEntity

interface OnMealClickListener {
    fun onMealClicked(meal: MealEntity)
}