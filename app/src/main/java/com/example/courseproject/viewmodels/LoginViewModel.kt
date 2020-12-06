package com.example.courseproject.viewmodels

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.courseproject.Constants.APP_PREFERENCES
import com.example.courseproject.Constants.AUTHORIZATION_STATE
import com.example.courseproject.Constants.IS_ADMIN
import com.example.courseproject.Constants.USER_ID
import com.example.courseproject.HotelRepository
import com.example.courseproject.fragments.LoginFragmentDirections
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.login.LoginEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    private val appPreferences = application.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

    var userLoginData: LoginEntity? = null

    var noLoginFound = false

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
    }

    private fun getUserLoginData(inputLogin: String): LoginEntity? {
       runBlocking {
           withContext(Dispatchers.IO) {
               userLoginData = repository.getUserLoginData(inputLogin)
           }
        }
        return userLoginData
    }

    fun authorizeUser(inputLogin: String, inputPassword: String, view: View) {
        val loginInfo = getUserLoginData(inputLogin)
        when {
            loginInfo?.password == inputPassword && loginInfo.isAdmin -> {
                appPreferences.edit().putBoolean(AUTHORIZATION_STATE, true).apply()
                appPreferences.edit().putBoolean(IS_ADMIN, true).apply()
                noLoginFound = false
                val action = LoginFragmentDirections.actionLoginFragmentToAdminMainFragment()
                view.findNavController().navigate(action)
            }

            loginInfo?.password == inputPassword && !loginInfo.isAdmin -> {
                appPreferences.edit().putBoolean(AUTHORIZATION_STATE, true).apply()
                appPreferences.edit().putInt(USER_ID, loginInfo.loginID).apply()
                appPreferences.edit().putBoolean(IS_ADMIN, false).apply()
                noLoginFound = false
                val action = LoginFragmentDirections.actionLoginFragmentToUserMainFragment(loginInfo.loginID)
                view.findNavController().navigate(action)
            }

            else -> noLoginFound = true
        }
    }
}