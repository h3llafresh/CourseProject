package com.example.courseproject.listeners

import com.example.courseproject.model.service.ServiceEntity

interface OnServiceClickListener {
    fun onServiceClicked(service: ServiceEntity)
}