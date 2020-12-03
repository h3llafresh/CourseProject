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
import com.example.courseproject.databinding.FragmentGuestInfoBinding
import com.example.courseproject.viewmodels.GuestInfoViewModel

class GuestInfoFragment : Fragment() {
    private var _binding: FragmentGuestInfoBinding? = null

    private val binding get() = _binding!!

    private val args: GuestInfoFragmentArgs by navArgs()

    private val guestInfoViewModel by viewModels<GuestInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuestInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val guestID = args.guestID
        guestInfoViewModel.selectGuest(guestID)
        val guest = guestInfoViewModel.guest
        binding.let {
            it.guestInfoFirstNameValue.text = guest.firstName
            it.guestInfoLastNameValue.text = guest.secondName
            it.guestInfoRoomNumberValue.text = guest.guestID.toString()
            it.guestInfoBirthDateValue.text = guest.birthDate
            it.guestInfoPhoneNumberValue.text = guest.phoneNumber.toString()
            it.guestInfoCheckIdDateValue.text = guest.checkInDate
            it.guestInfoCheckOutDateValue.text = guest.checkOutDate
            it.guestInfoIsRegularCustomerValue.text = intToYesOrNo(guest.isRegularCustomer)
            it.guestInfoHasExtraServiceValue.text = intToYesOrNo(guest.hasExtraService)
            it.guestInfoSumToPayValue.text = "${guest.sumToPay}$"
        }

        binding.fabGuestInfoUpdate.setOnClickListener {
            val action = GuestInfoFragmentDirections.actionGuestInfoFragmentToGuestUpdateFragment(guestID)
            findNavController().navigate(action)
        }

        binding.fabGuestInfoDelete.setOnClickListener {
            guestInfoViewModel.deleteGuest()
            findNavController().popBackStack()
            Toast.makeText(context, "Bye bye, our dear guest!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun intToYesOrNo(value: Int): String {
        return when (value) {
            1 -> "Yes"
            else -> "No"
        }
    }
}