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
import com.example.courseproject.databinding.FragmentServiceInfoGuestBinding
import com.example.courseproject.viewmodels.ServiceInfoGuestViewModel

class ServiceInfoGuestFragment : Fragment() {

    private var _binding: FragmentServiceInfoGuestBinding? = null

    private val binding get() = _binding!!

    private val serviceInfoViewModel by viewModels<ServiceInfoGuestViewModel>()

    private val args: ServiceInfoGuestFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceInfoGuestBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val serviceID = args.serviceID
        val guestID = args.guestID
        serviceInfoViewModel.selectService(serviceID)
        val service = serviceInfoViewModel.service
        binding.let {
            it.serviceInfoGuestNameValue.text = service.name
            it.serviceInfoGuestForChildrenValue.text = intToYesOrNo(service.suitableForChildren)
            it.serviceInfoGuestCostValue.text = "${service.cost}$"
        }

        binding.buttonOrder.setOnClickListener {
            serviceInfoViewModel.orderService(serviceID, guestID)
            findNavController().popBackStack()
            Toast.makeText(context, "Yay, our service master will come shortly!", Toast.LENGTH_SHORT).show()
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