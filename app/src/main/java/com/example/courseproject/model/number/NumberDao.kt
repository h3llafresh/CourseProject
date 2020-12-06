package com.example.courseproject.model.number

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumberDao {

    @Query("SELECT * FROM numbers WHERE numberID = :numberID")
    suspend fun selectHotelNumber(numberID: Int): NumberEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHotelNumber(hotelNumber: NumberEntity)
}