package com.example.courseproject.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.courseproject.databinding.GuestViewholderBinding
import com.example.courseproject.model.guest.GuestEntity

class GuestViewHolder(private val binding: GuestViewholderBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: GuestEntity) {
            val context = binding.root.context
            binding.guestNameViewholder.text = "${guest.firstName} ${guest.secondName}"
            //TODO: Add room entity
            binding.guestRoomViewholder.text = "Room 1505"
        }
}