package com.example.courseproject.listeners

import com.example.courseproject.model.guest.GuestEntity

interface OnGuestHolderClickListener {
    fun onGuestClicked(guest: GuestEntity)
}