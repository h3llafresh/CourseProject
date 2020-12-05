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
import com.example.courseproject.databinding.FragmentMealInfoGuestBinding
import com.example.courseproject.viewmodels.MealInfoGuestViewModel

class MealInfoGuestFragment : Fragment() {

    private var _binding: FragmentMealInfoGuestBinding? = null

    private val binding get() = _binding!!

    private val mealInfoViewModel by viewModels<MealInfoGuestViewModel>()

    private val args: MealInfoGuestFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealInfoGuestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val guestID = args.guestID
        val mealID = args.mealID
        mealInfoViewModel.selectMeal(mealID)
        val meal = mealInfoViewModel.meal
        binding.let {
            it.mealInfoGuestNameValue.text = meal.name
            it.mealInfoGuestTypeValue.text = meal.type
            it.mealInfoGuestDateValue.text = meal.date
            it.mealInfoGuestCostValue.text = "${meal.cost}$"
        }

        binding.buttonOrder.setOnClickListener {
            mealInfoViewModel.orderMeal(mealID, guestID)
            findNavController().popBackStack()
            Toast.makeText(context, "Ok, your meal is on the way!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}