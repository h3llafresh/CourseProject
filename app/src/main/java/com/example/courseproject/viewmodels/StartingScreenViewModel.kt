package com.example.courseproject.viewmodels

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.findNavController
import com.example.courseproject.fragments.StartingScreenFragmentDirections

class StartingScreenViewModel(application: Application) :
    AndroidViewModel(application) {
    fun routeToAppropriatePage(isAuthorized: Boolean, view: View) {
        if (isAuthorized) {
            //TODO: IMPLEMENT THIS CASE
        } else {
            val action = StartingScreenFragmentDirections.actionStartScreenFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }
    }
}