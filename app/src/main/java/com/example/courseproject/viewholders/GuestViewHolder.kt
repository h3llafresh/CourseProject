package com.example.courseproject.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.courseproject.databinding.GuestViewholderBinding
import com.example.courseproject.model.guest.GuestEntity

class GuestViewHolder(private val binding: GuestViewholderBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: GuestEntity) {
            binding.guestNameViewholder.text = "${guest.firstName} ${guest.secondName}"
            binding.guestRoomViewholder.text = "Room ${guest.guestID}"
        }
}