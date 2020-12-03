package com.example.courseproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.courseproject.databinding.GuestViewholderBinding
import com.example.courseproject.listeners.OnGuestHolderClickListener
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.viewholders.GuestViewHolder

class GuestAdapter(private val guestClickListener: OnGuestHolderClickListener) :
    RecyclerView.Adapter<GuestViewHolder>() {
    val guests: MutableList<GuestEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val binding =
            GuestViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = GuestViewHolder(binding)

        binding.root.setOnClickListener {
            val itemPosition = holder.bindingAdapterPosition

            if (itemPosition != RecyclerView.NO_POSITION)
               guestClickListener.onGuestClicked(guests[itemPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guests[position])
    }

    override fun getItemCount(): Int {
        return guests.size
    }

    fun addItems(newItems: List<GuestEntity>) {
        guests.clear()
        guests.addAll(newItems)
        notifyDataSetChanged()
    }
}