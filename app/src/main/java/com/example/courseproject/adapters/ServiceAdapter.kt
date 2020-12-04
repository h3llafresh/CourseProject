package com.example.courseproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.courseproject.databinding.ServiceViewholderBinding
import com.example.courseproject.listeners.OnServiceClickListener
import com.example.courseproject.model.service.ServiceEntity
import com.example.courseproject.viewholders.ServiceViewHolder

class ServiceAdapter(private val serviceClickListener: OnServiceClickListener) :
    RecyclerView.Adapter<ServiceViewHolder>() {
    val services: MutableList<ServiceEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding =
            ServiceViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ServiceViewHolder(binding)

        binding.root.setOnClickListener {
            val itemPosition = holder.bindingAdapterPosition

            if (itemPosition != RecyclerView.NO_POSITION) {
                serviceClickListener.onServiceClicked(services[itemPosition])
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(services[position])
    }

    override fun getItemCount(): Int {
        return services.size
    }

    fun addItems(newItems: List<ServiceEntity>) {
        services.clear()
        services.addAll(newItems)
        notifyDataSetChanged()
    }
}