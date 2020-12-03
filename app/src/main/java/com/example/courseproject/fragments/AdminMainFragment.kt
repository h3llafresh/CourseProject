package com.example.courseproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.courseproject.R
import com.example.courseproject.adapters.GuestAdapter
import com.example.courseproject.databinding.FragmentAdminMainBinding
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.viewmodels.AdminMainViewModel

class AdminMainFragment : Fragment() {

    private var _binding: FragmentAdminMainBinding? = null

    private val binding get() = _binding!!

    private val adminMainViewModel by viewModels<AdminMainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val welcomeText = binding.welcomeAdmin
        val recyclerGuests = binding.recyclerGuestsAdminMain
        val guestAdapter = GuestAdapter()
        val guests = mutableListOf(
            GuestEntity(
                1, "Travis", "Scott", "1", 1, "1",
                "2", 1, 12, 1, 12
            )
        )
        binding.bottomNavigationAdminMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_guests_admin -> {
                    welcomeText.visibility = GONE
                    recyclerGuests.visibility = VISIBLE
                }
                R.id.action_menu_admin -> {
                    welcomeText.visibility = GONE
                    recyclerGuests.visibility = GONE
                }
                R.id.action_services_admin -> {
                    welcomeText.visibility = VISIBLE
                    recyclerGuests.visibility = GONE
                }
            }
            true
        }

        guestAdapter.guests.addAll(guests)
        recyclerGuests.apply {
            adapter = guestAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        adminMainViewModel.guests.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            guestAdapter.addItems(it)
        })

        binding.floatingActionButton.setOnClickListener{
            adminMainViewModel.addGuest(GuestEntity(
                3, "Lebron", "James", "1", 1, "1",
                "2", 1, 12, 1, 12
            ))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}