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
import com.example.courseproject.databinding.FragmentAddUserBinding
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.viewmodels.AddGuestViewModel
import com.google.android.material.textfield.TextInputLayout

class GuestAddFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null

    private val binding get() = _binding!!

    private val addGuestViewModel by viewModels<AddGuestViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        setRalewayFont(binding.firstNameWrapper)
        setRalewayFont(binding.lastNameWrapper)
        setRalewayFont(binding.phoneNumberWrapper)
        setRalewayFont(binding.birthDateWrapper)
        setRalewayFont(binding.checkInWrapper)
        setRalewayFont(binding.checkOutWrapper)
        setRalewayFont(binding.sumToPayWrapper)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var isRegularCustomer = false
        var hasExtraService = false

        binding.radioRegularCustomer.setOnClickListener {
            isRegularCustomer = true
        }

        binding.radioExtraService.setOnClickListener {
            hasExtraService = true
        }

        binding.buttonAdd.setOnClickListener {
            val firstName = binding.firstNameInput.text.toString()
            val lastName = binding.lastNameInput.text.toString()
            val phoneNumber = binding.phoneNumberInput.text.toString()
            val birthDate = binding.birthDateInput.text.toString()
            val checkInDate = binding.checkInDateInput.text.toString()
            val checkOutDate = binding.checkOutDateInput.text.toString()
            val sumToPay = binding.sumToPayInput.text.toString()

            if (firstName.isNotEmpty()
                && lastName.isNotEmpty()
                && phoneNumber.isNotEmpty()
                && birthDate.isNotEmpty()
                && checkInDate.isNotEmpty()
                && checkOutDate.isNotEmpty()
                && sumToPay.isNotEmpty()
            ) {
                val guest = GuestEntity(
                    firstName = firstName,
                    secondName = lastName,
                    birthDate = birthDate,
                    phoneNumber = phoneNumber.toInt(),
                    checkInDate = checkInDate,
                    checkOutDate = checkOutDate,
                    sumToPay = sumToPay.toInt(),
                    hasExtraService = if (hasExtraService) 1 else 0,
                    isRegularCustomer = if (isRegularCustomer) 1 else 0
                )
                addGuestViewModel.addGuest(guest)
                findNavController().popBackStack()
                Toast.makeText(context, "Guest added!", Toast.LENGTH_SHORT).show()
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