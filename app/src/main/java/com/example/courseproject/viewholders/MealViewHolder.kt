package com.example.courseproject.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.courseproject.databinding.MealViewholderBinding
import com.example.courseproject.model.meal.MealEntity

class MealViewHolder(private val binding: MealViewholderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(meal: MealEntity) {
        binding.mealNameViewholder.text = meal.name
        binding.mealTypeViewholder.text = meal.type
        binding.mealCostViewholder.text = "${meal.cost}$"
    }
}