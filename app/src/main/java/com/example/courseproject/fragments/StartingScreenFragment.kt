package com.example.courseproject.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.courseproject.Constants.APP_PREFERENCES
import com.example.courseproject.Constants.AUTHORIZATION_STATE
import com.example.courseproject.R
import com.example.courseproject.viewmodels.StartingScreenViewModel

class StartingScreenFragment : Fragment(R.layout.fragment_start_screen) {

    private val startingScreenViewModel: StartingScreenViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appPreferences = requireContext().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        val isAuthorized = appPreferences.getBoolean(AUTHORIZATION_STATE, false)
        Handler(Looper.getMainLooper()).postDelayed(
            {startingScreenViewModel.routeToAppropriatePage(isAuthorized, view)}, 1000
        )
    }
}