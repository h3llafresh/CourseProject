package com.example.courseproject.model.guest

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GuestDao {

    @Query("SELECT * FROM guests")
    fun selectAllGuests(): Flow<List<GuestEntity>>

    @Query("SELECT * FROM guests WHERE guestID = :selectedGuestID")
    suspend fun selectGuest(selectedGuestID: Int): GuestEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGuest(guest: GuestEntity)

    @Update
    suspend fun updateGuest(guest: GuestEntity)

    @Delete
    suspend fun deleteGuest(guest: GuestEntity)
}