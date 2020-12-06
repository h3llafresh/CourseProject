package com.example.courseproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseproject.HotelRepository
import com.example.courseproject.model.HotelRoomDatabase
import com.example.courseproject.model.guest.GuestEntity
import com.example.courseproject.model.login.LoginEntity
import com.example.courseproject.model.number.NumberEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class GuestAddViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HotelRepository

    private var _number: NumberEntity? = null

    private val number get() = _number

    init {
        val hotelDatabase = HotelRoomDatabase.getDatabase(application, viewModelScope)
        repository = HotelRepository(hotelDatabase)
    }

    fun addGuest(guest: GuestEntity) {
        viewModelScope.launch {
            repository.addGuest(guest)
        }
    }

    fun addLogin(login: LoginEntity) {
        viewModelScope.launch {
            repository.insertLogin(login)
        }
    }

    private fun selectHotelNumber(roomNumber: Int) {
        runBlocking {
            _number = repository.selectHotelNumber(roomNumber)
        }
    }


    fun calculateCost(roomNumber: Int, checkInDate: String, checkOutDate: String, isRegularCustomer: Boolean): Int {
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        val diffInMillies: Long = abs(formatter.parse(checkOutDate)!!.time - formatter.parse(checkInDate)!!.time);
        val daysInHotel = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS).toInt()
        selectHotelNumber(roomNumber)
        val cost = number!!.cost * daysInHotel
        return if (isRegularCustomer){
                (cost * 0.8).toInt()
        } else cost
    }
}