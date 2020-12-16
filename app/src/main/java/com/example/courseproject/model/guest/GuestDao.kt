package com.example.courseproject.model.guest

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GuestDao {

    @Query("SELECT * FROM Guest")
    fun selectAllGuests(): Flow<List<GuestEntity>>

    @Query("SELECT * FROM Guest WHERE guestID = :selectedGuestID")
    suspend fun selectGuest(selectedGuestID: Int): GuestEntity

    @Query("UPDATE Guest SET sumToPay = sumToPay + " +
            "(SELECT cost FROM Meal WHERE mealID = :selectedMealID) WHERE guestID = :selectedGuestID")
    suspend fun orderMeal(selectedMealID: Int, selectedGuestID: Int)

    @Query("UPDATE Guest SET sumToPay = sumToPay" +
            " + (SELECT cost FROM Service WHERE serviceID = :selectedServiceID) WHERE guestID = :selectedGuestID")
    suspend fun orderService(selectedServiceID: Int, selectedGuestID: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGuest(guest: GuestEntity)

    @Update
    suspend fun updateGuest(guest: GuestEntity)

    @Delete
    suspend fun deleteGuest(guest: GuestEntity)
}