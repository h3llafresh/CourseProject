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
import androidx.navigation.fragment.navArgs
import com.example.courseproject.R
import com.example.courseproject.databinding.FragmentMealUpdateBinding
import com.example.courseproject.model.meal.MealEntity
import com.example.courseproject.viewmodels.MealUpdateViewModel
import com.google.android.material.textfield.TextInputLayout

class MealUpdateFragment : Fragment() {

    private var _binding: FragmentMealUpdateBinding? = null

    private val binding get() = _binding!!

    private val updateMealViewModel by viewModels<MealUpdateViewModel>()

    private val args: MealUpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealUpdateBinding.inflate(inflater, container, false)
        setRalewayFont(binding.updateMealNameWrapper)
        setRalewayFont(binding.updateMealTypeWrapper)
        setRalewayFont(binding.updateMealDateWrapper)
        setRalewayFont(binding.updateMealCostWrapper)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.updateButtonUpdate.setOnClickListener {
            val mealID = args.mealID
            val mealName = binding.updateMealNameInput.text.toString()
            val mealType = binding.updateMealTypeInput.text.toString()
            val mealDate = binding.updateMealDateInput.text.toString()
            val mealCost = binding.updateMealCostInput.text.toString()

            if (mealName.isNotEmpty()
                && mealType.isNotEmpty()
                && mealDate.isNotEmpty()
                && mealCost.isNotEmpty()
            ) {
                val meal = MealEntity(
                    mealID = mealID,
                    name = mealName,
                    type = mealType,
                    date = mealDate,
                    cost = mealCost.toInt()
                )
                updateMealViewModel.updateMeal(meal)
                findNavController().popBackStack()
                Toast.makeText(context, "Meal updated!", Toast.LENGTH_SHORT).show()
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