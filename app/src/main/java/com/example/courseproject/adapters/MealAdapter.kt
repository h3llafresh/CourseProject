package com.example.courseproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.courseproject.databinding.MealViewholderBinding
import com.example.courseproject.listeners.OnMealClickListener
import com.example.courseproject.model.meal.MealEntity
import com.example.courseproject.viewholders.MealViewHolder

class MealAdapter(private val mealClickListener: OnMealClickListener) :
    RecyclerView.Adapter<MealViewHolder>() {
    val meals: MutableList<MealEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding =
            MealViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MealViewHolder(binding)

        binding.root.setOnClickListener {
            val itemPosition = holder.bindingAdapterPosition

            if (itemPosition != RecyclerView.NO_POSITION) {
                mealClickListener.onMealClicked(meals[itemPosition])
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    fun addItems(newItems: List<MealEntity>) {
        meals.clear()
        meals.addAll(newItems)
        notifyDataSetChanged()
    }
}