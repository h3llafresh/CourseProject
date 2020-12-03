package com.example.courseproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.courseproject.R
import com.example.courseproject.databinding.FragmentUserMainBinding

class GuestMainFragment : Fragment() {
    private var _binding: FragmentUserMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigationUserMain.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.action_expenses_user -> {
                    binding.welcomeUser.visibility = GONE
                }
                R.id.action_menu_user -> {
                    binding.welcomeUser.visibility = GONE
                }
                R.id.action_services_user -> {
                    binding.welcomeUser.visibility = GONE
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