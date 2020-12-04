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
import com.example.courseproject.databinding.FragmentServiceUpdateBinding
import com.example.courseproject.model.service.ServiceEntity
import com.example.courseproject.viewmodels.ServiceUpdateViewModel
import com.google.android.material.textfield.TextInputLayout

class ServiceUpdateFragment : Fragment() {

    private var _binding: FragmentServiceUpdateBinding? = null

    private val binding get() = _binding!!

    private val updateServiceViewModel by viewModels<ServiceUpdateViewModel>()

    private val args: ServiceUpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceUpdateBinding.inflate(inflater, container, false)
        setRalewayFont(binding.updateServiceNameWrapper)
        setRalewayFont(binding.updateServiceCostWrapper)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isSuitableForChildren = false

        binding.updateRadioSuitableForChildren.setOnClickListener {
            isSuitableForChildren = true
        }

        binding.buttonUpdate.setOnClickListener {
            val serviceID = args.serviceID
            val serviceName = binding.updateServiceNameInput.text.toString()
            val serviceCost = binding.updateServiceCostInput.text.toString()

            if (serviceName.isNotEmpty() && serviceCost.isNotEmpty()
            ) {
                val service = ServiceEntity(
                    serviceID = serviceID,
                    name = serviceName,
                    cost = serviceCost.toInt(),
                    suitableForChildren = if (isSuitableForChildren) 1 else 0
                )
                updateServiceViewModel.updateService(service)
                findNavController().popBackStack()
                Toast.makeText(context, "Service updated!", Toast.LENGTH_SHORT).show()
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