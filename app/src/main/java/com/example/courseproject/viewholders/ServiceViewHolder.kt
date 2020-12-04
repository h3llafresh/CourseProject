package com.example.courseproject.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.courseproject.databinding.ServiceViewholderBinding
import com.example.courseproject.model.service.ServiceEntity

class ServiceViewHolder(private val binding: ServiceViewholderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(service: ServiceEntity) {
        binding.serviceNameViewholder.text = service.name
        binding.serviceCostViewholder.text = "${service.cost}$"
    }
}