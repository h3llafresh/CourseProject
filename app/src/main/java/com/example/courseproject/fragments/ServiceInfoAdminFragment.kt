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
import com.example.courseproject.databinding.FragmentServiceInfoAdminBinding
import com.example.courseproject.viewmodels.ServiceInfoAdminViewModel

class ServiceInfoAdminFragment : Fragment() {

    private var _binding: FragmentServiceInfoAdminBinding? = null

    private val binding get() = _binding!!

    private val serviceInfoViewModel by viewModels<ServiceInfoAdminViewModel>()

    private val args: ServiceInfoAdminFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceInfoAdminBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val serviceID = args.serviceID
        serviceInfoViewModel.selectService(serviceID)
        val service = serviceInfoViewModel.service
        binding.let {
            it.serviceInfoAdminNameValue.text = service.name
            it.serviceInfoAdminForChildrenValue.text = intToYesOrNo(service.suitableForChildren)
            it.serviceInfoAdminCostValue.text = "${service.cost}$"
        }

        binding.fabServiceInfoUpdate.setOnClickListener {
            val action =
                ServiceInfoAdminFragmentDirections.actionServiceInfoAdminFragmentToServiceUpdateFragment(
                    serviceID
                )
            findNavController().navigate(action)
        }

        binding.fabServiceInfoDelete.setOnClickListener {
            serviceInfoViewModel.deleteService(service)
            findNavController().popBackStack()
            Toast.makeText(context, "Ok, service is cleared!", Toast.LENGTH_LONG).show()
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