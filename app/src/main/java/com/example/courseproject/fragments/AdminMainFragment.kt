package com.example.courseproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.courseproject.R
import com.example.courseproject.adapters.GuestAdapter
import com.example.courseproject.databinding.FragmentAdminMainBinding
import com.example.courseproject.listeners.OnGuestHolderClickListener
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.viewmodels.AdminMainViewModel

class AdminMainFragment : Fragment(), OnGuestHolderClickListener {

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
        val bottomNav = binding.bottomNavigationAdminMain
        val recyclerGuests = binding.recyclerGuestsAdminMain
        val guestAdapter = GuestAdapter(this)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_guests_admin -> {
                    recyclerGuests.visibility = VISIBLE
                }
                R.id.action_menu_admin -> {
                    recyclerGuests.visibility = GONE
                }
                R.id.action_services_admin -> {
                    recyclerGuests.visibility = GONE
                }
            }
            true
        }

        recyclerGuests.apply {
            adapter = guestAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        adminMainViewModel.guests.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            guestAdapter.addItems(it)
        })

        binding.floatingActionButton.setOnClickListener {
            when (bottomNav.selectedItemId) {
                R.id.action_guests_admin -> {
                    val action =
                        AdminMainFragmentDirections.actionAdminMainFragmentToAddUserFragment()
                    findNavController().navigate(action)
                }
                R.id.action_menu_admin -> Toast.makeText(context, "Add dish", Toast.LENGTH_SHORT)
                    .show()
                R.id.action_services_admin -> Toast.makeText(
                    context,
                    "Add service",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = binding.bottomNavigationAdminMain
        val recyclerGuests = binding.recyclerGuestsAdminMain
        when (bottomNav.selectedItemId) {
            R.id.action_guests_admin -> {
                recyclerGuests.visibility = VISIBLE
            }
            R.id.action_menu_admin -> {
                recyclerGuests.visibility = GONE
            }
            R.id.action_services_admin -> {
                recyclerGuests.visibility = GONE
            }
        }
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }

    override fun onGuestClicked(guest: GuestEntity) {
        val action =
            AdminMainFragmentDirections.actionAdminMainFragmentToGuestInfoFragment(guest.guestID)
        findNavController().navigate(action)
    }
}