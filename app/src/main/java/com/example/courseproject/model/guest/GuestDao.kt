package com.example.courseproject.model.guest

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GuestDao {

    @Query("SELECT * FROM guests")
    fun selectAllGuests(): Flow<List<GuestEntity>>

    @Query("SELECT * FROM guests WHERE guestID = :selectedGuestID")
    suspend fun selectGuest(selectedGuestID: Int): GuestEntity

    @Query("UPDATE guests SET sumToPay = sumToPay + " +
            "(SELECT cost FROM meals WHERE mealID = :selectedMealID) WHERE guestID = :selectedGuestID")
    suspend fun orderMeal(selectedMealID: Int, selectedGuestID: Int)

    @Query("UPDATE guests SET sumToPay = sumToPay" +
            " + (SELECT cost FROM services WHERE serviceID = :selectedServiceID) WHERE guestID = :selectedGuestID")
    suspend fun orderService(selectedServiceID: Int, selectedGuestID: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGuest(guest: GuestEntity)

    @Update
    suspend fun updateGuest(guest: GuestEntity)

    @Delete
    suspend fun deleteGuest(guest: GuestEntity)
}