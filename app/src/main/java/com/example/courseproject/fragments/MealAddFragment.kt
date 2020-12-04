package com.example.courseproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.courseproject.R
import com.example.courseproject.databinding.FragmentMealAddBinding
import com.example.courseproject.model.meal.MealEntity
import com.example.courseproject.viewmodels.MealAddViewModel
import com.google.android.material.textfield.TextInputLayout

class MealAddFragment : Fragment() {

    private var _binding: FragmentMealAddBinding? = null

    private val binding get() = _binding!!

    private val addMealViewModel by viewModels<MealAddViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealAddBinding.inflate(inflater, container, false)
        setRalewayFont(binding.mealNameWrapper)
        setRalewayFont(binding.mealTypeWrapper)
        setRalewayFont(binding.mealDateWrapper)
        setRalewayFont(binding.mealCostWrapper)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdd.setOnClickListener {
            val mealName = binding.mealNameInput.text.toString()
            val mealType = binding.mealTypeInput.text.toString()
            val mealDate = binding.mealDateInput.text.toString()
            val mealCost = binding.mealCostInput.text.toString()

            if (mealName.isNotEmpty()
                && mealType.isNotEmpty()
                && mealDate.isNotEmpty()
                && mealCost.isNotEmpty()
            ) {
                val meal = MealEntity(
                    name = mealName,
                    type = mealType,
                    date = mealDate,
                    cost = mealCost.toInt()
                )
                addMealViewModel.addMeal(meal)
                findNavController().popBackStack()
                Toast.makeText(context, "Meal added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Not enough data, sorry.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setRalewayFont(wrapper: TextInputLayout) {
        wrapper.typeface = ResourcesCompat.getFont(
            requireContext(),
            R.font.raleway
        )
    }
}