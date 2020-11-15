package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.db.HotelRoomDatabase
import com.example.courseproject.db.LoginEntity
import kotlinx.coroutines.*

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HotelRepository

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

    fun authorizeUser(inputLogin: String, inputPassword: String): String {
        val loginInfo = getUserLoginData(inputLogin)
        if (loginInfo?.password == inputPassword) {
            if (loginInfo.isAdmin == true) {
                return "Cheery greetings to out great admin!"
            } else return "Hello out dear customer!"
        } else return "Wrong login or password, please try again"
    }
}