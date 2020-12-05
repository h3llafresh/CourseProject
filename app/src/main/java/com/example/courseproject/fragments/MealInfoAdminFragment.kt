package com.example.courseproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.courseproject.databinding.FragmentMealInfoAdminBinding
import com.example.courseproject.viewmodels.MealInfoAdminViewModel

class MealInfoAdminFragment : Fragment() {
    private var _binding: FragmentMealInfoAdminBinding? = null

    private val binding get() = _binding!!

    private val mealInfoViewModel by viewModels<MealInfoAdminViewModel>()

    private val args: MealInfoAdminFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealInfoAdminBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mealID = args.mealID
        mealInfoViewModel.selectMeal(mealID)
        val meal = mealInfoViewModel.meal
        binding.let {
            it.mealInfoAdminNameValue.text = meal.name
            it.mealInfoAdminTypeValue.text = meal.type
            it.mealInfoAdminDateValue.text = meal.date
            it.mealInfoAdminCostValue.text = "${meal.cost}$"
        }

        binding.fabMealInfoUpdate.setOnClickListener {
            val action = MealInfoAdminFragmentDirections.actionMealInfoAdminFragmentToMealUpdateFragment(mealID)
            findNavController().navigate(action)
        }

        binding.fabMealInfoDelete.setOnClickListener {
            mealInfoViewModel.deleteMeal()
            findNavController().popBackStack()
            Toast.makeText(context, "Meal has been eaten!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}