package com.example.courseproject.model.number

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numbers")
data class NumberEntity(
    @PrimaryKey val numberID: Int,
    @ColumnInfo val hasSeaView: Int,
    @ColumnInfo val numberOfRooms: Int,
    @ColumnInfo val numberOfBeds: Int,
    @ColumnInfo val hasBalcony: Int,
    @ColumnInfo val isMultifloor: Int,
    @ColumnInfo val interiorLevel: String,
    @ColumnInfo val cost: Int
)
