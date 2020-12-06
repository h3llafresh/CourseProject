package com.example.courseproject.model.guest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guests")
data class GuestEntity(
    @PrimaryKey(autoGenerate = true) val guestID: Int = 0,
    @ColumnInfo val firstName: String,
    @ColumnInfo val secondName: String,
    @ColumnInfo val roomNumber: Int,
    @ColumnInfo val birthDate: String,
    @ColumnInfo val phoneNumber: Int,
    @ColumnInfo val checkInDate: String,
    @ColumnInfo val checkOutDate: String,
    @ColumnInfo val hasExtraService: Int,
    @ColumnInfo val sumToPay: Int,
    @ColumnInfo val isRegularCustomer: Int,
    @ColumnInfo val guestLoginID: Int = guestID
    )


