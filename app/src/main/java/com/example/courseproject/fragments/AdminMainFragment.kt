package com.example.courseproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.courseproject.R
import com.example.courseproject.databinding.FragmentAdminMainBinding

class AdminMainFragment : Fragment() {

    private var _binding: FragmentAdminMainBinding? = null

    private val binding get() = _binding!!

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
        binding.bottomNavigationAdminMain.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.action_guests_admin -> {
                    welcomeText.visibility = GONE
                }
                R.id.action_menu_admin -> {
                    welcomeText.visibility = GONE
                }
                R.id.action_services_admin -> {
                    welcomeText.visibility = VISIBLE
                }
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}