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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.courseproject.R
import com.example.courseproject.adapters.MealAdapter
import com.example.courseproject.adapters.ServiceAdapter
import com.example.courseproject.databinding.FragmentGuestMainBinding
import com.example.courseproject.listeners.OnMealClickListener
import com.example.courseproject.listeners.OnServiceClickListener
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.model.meal.MealEntity
import com.example.courseproject.model.service.ServiceEntity
import com.example.courseproject.viewmodels.GuestMainViewModel

class GuestMainFragment : Fragment(), OnMealClickListener, OnServiceClickListener {
    private var _binding: FragmentGuestMainBinding? = null

    private val binding get() = _binding!!

    private val guestMainViewModel by viewModels<GuestMainViewModel>()

    private val args: GuestMainFragmentArgs by navArgs()

    private var guest: GuestEntity? = null

    private var guestID: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuestMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        guestID = args.guestID - 1
        guestMainViewModel.selectGuest(guestID!!)
        guest = guestMainViewModel.guest
        val bottomNav = binding.bottomNavigationGuestMain
        val recyclerMeals = binding.recyclerMenuGuestMain
        val recyclerServices = binding.recyclerServicesGuestMain
        val noServicesText = binding.noExtrasText
        val getAccessText = binding.getAccessText
        val expensesText = binding.expensesTextView
        val expensesValue = binding.expensesTextValue
        val sortButton = binding.fragmentGuestMainToolbar.sortButton
        val mealAdapter = MealAdapter(this)
        val serviceAdapter = ServiceAdapter(this)

        expensesValue.text = "${guest?.sumToPay.toString()}$"

        binding.fragmentGuestMainToolbar.sortButton.setOnClickListener {
            when (bottomNav.selectedItemId) {
                R.id.action_menu_user -> {
                    mealAdapter.addItems(mealAdapter.meals.sortedBy { it.name })
                }
                R.id.action_services_user -> {
                    serviceAdapter.addItems(serviceAdapter.services.sortedBy { it.name })
                }
            }
        }

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_expenses_user -> {
                    sortButton.visibility = GONE
                    expensesText.visibility = VISIBLE
                    expensesValue.visibility = VISIBLE
                    noServicesText.visibility = GONE
                    getAccessText.visibility = GONE
                    recyclerMeals.visibility = GONE
                    recyclerServices.visibility = GONE
                }
                R.id.action_menu_user -> {
                    sortButton.visibility = VISIBLE
                    expensesText.visibility = GONE
                    expensesValue.visibility = GONE
                    if (guest?.hasExtraService == 1) {
                        recyclerMeals.visibility = VISIBLE
                        noServicesText.visibility = GONE
                        getAccessText.visibility = GONE
                    } else {
                        sortButton.visibility = GONE
                        recyclerMeals.visibility = GONE
                        noServicesText.visibility = VISIBLE
                        getAccessText.visibility = VISIBLE
                    }
                    recyclerServices.visibility = GONE
                }
                R.id.action_services_user -> {
                    sortButton.visibility = VISIBLE
                    expensesText.visibility = GONE
                    expensesValue.visibility = GONE
                    if (guest?.hasExtraService == 1) {
                        recyclerServices.visibility = VISIBLE
                        noServicesText.visibility = GONE
                        getAccessText.visibility = GONE
                    } else {
                        sortButton.visibility = GONE
                        recyclerServices.visibility = GONE
                        noServicesText.visibility = VISIBLE
                        getAccessText.visibility = VISIBLE
                    }
                    recyclerMeals.visibility = GONE
                }
            }
            true
        }

        binding.fragmentGuestMainToolbar.exitButton.setOnClickListener {
            guestMainViewModel.exitFromAccount()
            val action = GuestMainFragmentDirections.actionUserMainFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        recyclerMeals.apply {
            adapter = mealAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        recyclerServices.apply {
            adapter = serviceAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        guestMainViewModel.meals.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            mealAdapter.addItems(it)
        })

        guestMainViewModel.services.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            serviceAdapter.addItems(it)
        })
    }

    override fun onStart() {
        super.onStart()
        val bottomNav = binding.bottomNavigationGuestMain
        val recyclerMeals = binding.recyclerMenuGuestMain
        val recyclerServices = binding.recyclerServicesGuestMain
        val noServicesText = binding.noExtrasText
        val getAccessText = binding.getAccessText
        val sortButton = binding.fragmentGuestMainToolbar.sortButton
        val expensesText = binding.expensesTextView
        val expensesValue = binding.expensesTextValue
        when (bottomNav.selectedItemId) {
            R.id.action_expenses_user -> {
                sortButton.visibility = GONE
                expensesText.visibility = VISIBLE
                expensesValue.visibility = VISIBLE
                recyclerMeals.visibility = GONE
                noServicesText.visibility = GONE
                getAccessText.visibility = GONE
                recyclerServices.visibility = GONE
            }

            R.id.action_menu_user -> {
                sortButton.visibility = VISIBLE
                expensesText.visibility = GONE
                expensesValue.visibility = GONE
                if (guest?.hasExtraService == 1) {
                    recyclerMeals.visibility = VISIBLE
                    noServicesText.visibility = GONE
                    getAccessText.visibility = GONE
                } else {
                    recyclerMeals.visibility = GONE
                    noServicesText.visibility = VISIBLE
                    getAccessText.visibility = VISIBLE
                }
                recyclerServices.visibility = GONE
            }

            R.id.action_services_user -> {
                sortButton.visibility = VISIBLE
                expensesText.visibility = GONE
                expensesValue.visibility = GONE
                recyclerMeals.visibility = GONE
                if (guest?.hasExtraService == 1) {
                    recyclerServices.visibility = VISIBLE
                    noServicesText.visibility = GONE
                    getAccessText.visibility = GONE
                } else {
                    recyclerServices.visibility = GONE
                    noServicesText.visibility = VISIBLE
                    getAccessText.visibility = VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMealClicked(meal: MealEntity) {
        val action = GuestMainFragmentDirections.actionUserMainFragmentToMealInfoGuestFragment(
            guest?.guestID ?: 0, meal.mealID
        )
        findNavController().navigate(action)
    }

    override fun onServiceClicked(service: ServiceEntity) {
        val action = GuestMainFragmentDirections.actionUserMainFragmentToServiceInfoGuestFragment(
            guest?.guestID ?: 0, service.serviceID
        )
        findNavController().navigate(action)
    }
}