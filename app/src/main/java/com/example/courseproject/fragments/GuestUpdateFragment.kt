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
import com.example.courseproject.databinding.FragmentGuestUpdateBinding
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.model.login.LoginEntity
import com.example.courseproject.viewmodels.GuestUpdateViewModel
import com.google.android.material.textfield.TextInputLayout

class GuestUpdateFragment : Fragment() {
    private var _binding: FragmentGuestUpdateBinding? = null

    private val binding get() = _binding!!

    private val guestUpdateViewModel by viewModels<GuestUpdateViewModel>()

    private val args: GuestUpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuestUpdateBinding.inflate(inflater, container, false)
        setRalewayFont(binding.firstNameWrapperUpdate)
        setRalewayFont(binding.lastNameWrapperUpdate)
        setRalewayFont(binding.roomNumberWrapperUpdate)
        setRalewayFont(binding.phoneNumberWrapperUpdate)
        setRalewayFont(binding.birthDateWrapperUpdate)
        setRalewayFont(binding.checkInWrapperUpdate)
        setRalewayFont(binding.checkOutWrapperUpdate)
        setRalewayFont(binding.sumToPayWrapperUpdate)
        setRalewayFont(binding.updateUserLoginWrapper)
        setRalewayFont(binding.updateUserPasswordWrapper)
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

        binding.buttonUpdate.setOnClickListener {
            val guestID = args.guestID
            val firstName = binding.firstNameInputUpdate.text.toString()
            val lastName = binding.lastNameInputUpdate.text.toString()
            val roomNumber = binding.roomNumberInputUpdate.text.toString()
            val phoneNumber = binding.phoneNumberInputUpdate.text.toString()
            val birthDate = binding.birthDateInputUpdate.text.toString()
            val checkInDate = binding.checkInDateInputUpdate.text.toString()
            val checkOutDate = binding.checkOutDateInputUpdate.text.toString()
            val sumToPay = binding.sumToPayInputUpdate.text.toString()
            val login = binding.updateUserLoginInput.text.toString()
            val password = binding.updateUserPasswordInput.text.toString()

            if (firstName.isNotEmpty()
                && lastName.isNotEmpty()
                && phoneNumber.isNotEmpty()
                && birthDate.isNotEmpty()
                && checkInDate.isNotEmpty()
                && checkOutDate.isNotEmpty()
                && sumToPay.isNotEmpty()
            ) {
                val guest = GuestEntity(
                    guestID = guestID,
                    firstName = firstName,
                    secondName = lastName,
                    roomNumber = roomNumber.toInt(),
                    birthDate = birthDate,
                    phoneNumber = phoneNumber.toInt(),
                    checkInDate = checkInDate,
                    checkOutDate = checkOutDate,
                    sumToPay = if (sumToPay.toInt() == 0) {
                        guestUpdateViewModel.calculateCost(
                            roomNumber.toInt(),
                            checkInDate,
                            checkOutDate,
                            isRegularCustomer
                        )
                    } else sumToPay.toInt(),
                    hasExtraService = if (hasExtraService) 1 else 0,
                    isRegularCustomer = if (isRegularCustomer) 1 else 0
                )

                val loginInfo = LoginEntity(
                    loginID = guestID + 1,
                    login = login,
                    password = password,
                    isAdmin = false
                )
                guestUpdateViewModel.updateGuest(guest)
                guestUpdateViewModel.updateLogin(loginInfo)
                findNavController().popBackStack()
                Toast.makeText(context, "Guest updated!", Toast.LENGTH_SHORT).show()
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