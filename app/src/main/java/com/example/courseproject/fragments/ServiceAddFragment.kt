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
import com.example.courseproject.databinding.FragmentServiceAddBinding
import com.example.courseproject.model.service.ServiceEntity
import com.example.courseproject.viewmodels.ServiceAddViewModel
import com.google.android.material.textfield.TextInputLayout

class ServiceAddFragment : Fragment() {

    private var _binding: FragmentServiceAddBinding? = null

    private val binding get() = _binding!!

    private val addServiceViewModel by viewModels<ServiceAddViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceAddBinding.inflate(inflater, container, false)
        setRalewayFont(binding.serviceNameWrapper)
        setRalewayFont(binding.serviceCostWrapper)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isSuitableForChildren = false

        binding.radioSuitableForChildren.setOnClickListener {
            isSuitableForChildren = true
        }

        binding.buttonAdd.setOnClickListener {
            val serviceName = binding.serviceNameInput.text.toString()
            val serviceCost = binding.serviceCostInput.text.toString()

            if (serviceName.isNotEmpty() && serviceCost.isNotEmpty()) {
            val service = ServiceEntity(
                name = serviceName,
                cost = serviceCost.toInt(),
                suitableForChildren = if (isSuitableForChildren) 1 else 0
            )
            addServiceViewModel.addService(service)
            findNavController().popBackStack()
            Toast.makeText(context, "Service added!", Toast.LENGTH_SHORT).show()
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