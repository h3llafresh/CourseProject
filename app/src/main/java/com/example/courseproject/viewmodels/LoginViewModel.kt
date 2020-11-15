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
import com.example.courseproject.HotelRepository
import com.example.courseproject.db.HotelRoomDatabase
import com.example.courseproject.db.LoginEntity
import com.example.courseproject.fragments.LoginFragmentDirections
import kotlinx.coroutines.*

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

    private val appPreferences = application.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

    var userLoginData: LoginEntity? = null

    init {
        val loginDao = HotelRoomDatabase.getDatabase(application, viewModelScope).loginDao()
        repository = HotelRepository(loginDao)
    }

    fun insert(newLogin: LoginEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertLogin(newLogin)
    }

    private fun getUserLoginData(inputLogin: String): LoginEntity? {
        val loginEntityDef: Deferred<LoginEntity?> = viewModelScope.async(Dispatchers.IO) {
            repository.getUserLoginData(inputLogin)
        }
        return runBlocking { loginEntityDef.await() }
    }

    fun authorizeUser(inputLogin: String, inputPassword: String, view: View) {
        val loginInfo = getUserLoginData(inputLogin)
        when {
            loginInfo?.password == inputPassword && loginInfo.isAdmin -> {
                appPreferences.edit().putBoolean(AUTHORIZATION_STATE, true).apply()
                appPreferences.edit().putBoolean(IS_ADMIN, true).apply()
                val action = LoginFragmentDirections.actionLoginFragmentToAdminMainFragment()
                view.findNavController().navigate(action)
            }
            loginInfo?.password == inputPassword -> {
                appPreferences.edit().putBoolean(AUTHORIZATION_STATE, true).apply()
                appPreferences.edit().putBoolean(IS_ADMIN, false).apply()
                val action = LoginFragmentDirections.actionLoginFragmentToUserMainFragment()
                view.findNavController().navigate(action)
            }
        }
    }
}