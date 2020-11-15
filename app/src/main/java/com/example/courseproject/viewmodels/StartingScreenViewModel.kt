package com.example.courseproject.viewmodels

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.findNavController
import com.example.courseproject.Constants
import com.example.courseproject.Constants.APP_PREFERENCES
import com.example.courseproject.Constants.AUTHORIZATION_STATE
import com.example.courseproject.Constants.IS_ADMIN
import com.example.courseproject.fragments.StartingScreenFragmentDirections

class StartingScreenViewModel(application: Application) :
    AndroidViewModel(application) {

    private val appPreferences = application.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

    fun routeToLoginOrMain(view: View) {
        Handler(Looper.getMainLooper()).postDelayed({
            val isAuthorized = appPreferences.getBoolean(AUTHORIZATION_STATE, false)
            if (isAuthorized) {
                val isAdmin = appPreferences.getBoolean(IS_ADMIN, false)
                if (isAdmin) {
                    val action =
                        StartingScreenFragmentDirections.actionStartScreenFragmentToAdminMainFragment()
                    view.findNavController().navigate(action)
                } else {
                    val action =
                        StartingScreenFragmentDirections.actionStartScreenFragmentToUserMainFragment()
                    view.findNavController().navigate(action)
                }
            } else {
                val action =
                    StartingScreenFragmentDirections.actionStartScreenFragmentToLoginFragment()
                view.findNavController().navigate(action)
            }
        }, 1000)
    }
}