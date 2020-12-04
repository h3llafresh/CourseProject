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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.courseproject.R
import com.example.courseproject.adapters.GuestAdapter
import com.example.courseproject.adapters.MealAdapter
import com.example.courseproject.adapters.ServiceAdapter
import com.example.courseproject.databinding.FragmentAdminMainBinding
import com.example.courseproject.listeners.OnGuestHolderClickListener
import com.example.courseproject.listeners.OnMealClickListener
import com.example.courseproject.listeners.OnServiceClickListener
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.model.meal.MealEntity
import com.example.courseproject.model.service.ServiceEntity
import com.example.courseproject.viewmodels.AdminMainViewModel

class AdminMainFragment : Fragment(), OnGuestHolderClickListener, OnMealClickListener,
    OnServiceClickListener {

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
        val recyclerMeals = binding.recyclerMenuAdminMain
        val recyclerServices = binding.recyclerServicesAdminMain
        val guestAdapter = GuestAdapter(this)
        val mealAdapter = MealAdapter(this)
        val serviceAdapter = ServiceAdapter(this)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_guests_admin -> {
                    recyclerGuests.visibility = VISIBLE
                    recyclerMeals.visibility = GONE
                    recyclerServices.visibility = GONE
                }
                R.id.action_menu_admin -> {
                    recyclerGuests.visibility = GONE
                    recyclerMeals.visibility = VISIBLE
                    recyclerServices.visibility = GONE
                }
                R.id.action_services_admin -> {
                    recyclerGuests.visibility = GONE
                    recyclerMeals.visibility = GONE
                    recyclerServices.visibility = VISIBLE
                }
            }
            true
        }

        recyclerGuests.apply {
            adapter = guestAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        recyclerMeals.apply {
            adapter = mealAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        recyclerServices.apply {
            adapter = serviceAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        adminMainViewModel.guests.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            guestAdapter.addItems(it)
        })

        adminMainViewModel.meals.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            mealAdapter.addItems(it)
        })

        adminMainViewModel.services.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            serviceAdapter.addItems(it)
        })

        binding.floatingActionButton.setOnClickListener {
            when (bottomNav.selectedItemId) {
                R.id.action_guests_admin -> {
                    val action =
                        AdminMainFragmentDirections.actionAdminMainFragmentToAddUserFragment()
                    findNavController().navigate(action)
                }
                R.id.action_menu_admin -> {
                    val action =
                        AdminMainFragmentDirections.actionAdminMainFragmentToMealAddFragment()
                    findNavController().navigate(action)
                }
                R.id.action_services_admin -> {
                    val action =
                        AdminMainFragmentDirections.actionAdminMainFragmentToServiceAddFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = binding.bottomNavigationAdminMain
        val recyclerGuests = binding.recyclerGuestsAdminMain
        val recyclerMeals = binding.recyclerMenuAdminMain
        val recyclerServices = binding.recyclerServicesAdminMain
        when (bottomNav.selectedItemId) {
            R.id.action_guests_admin -> {
                recyclerGuests.visibility = VISIBLE
                recyclerMeals.visibility = GONE
                recyclerServices.visibility = GONE
            }

            R.id.action_menu_admin -> {
                recyclerGuests.visibility = GONE
                recyclerMeals.visibility = VISIBLE
                recyclerServices.visibility = GONE
            }

            R.id.action_services_admin -> {
                recyclerGuests.visibility = GONE
                recyclerMeals.visibility = GONE
                recyclerServices.visibility = VISIBLE
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

    override fun onMealClicked(meal: MealEntity) {
        val action =
            AdminMainFragmentDirections.actionAdminMainFragmentToMealInfoAdminFragment(meal.mealID)
        findNavController().navigate(action)
    }

    override fun onServiceClicked(service: ServiceEntity) {
        val action =
            AdminMainFragmentDirections.actionAdminMainFragmentToServiceInfoAdminFragment(service.serviceID)
        findNavController().navigate(action)
    }
}