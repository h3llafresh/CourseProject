package com.example.courseproject.model.service

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Service")
data class ServiceEntity(
    @PrimaryKey(autoGenerate = true) val serviceID: Int = 0,
    @ColumnInfo val name: String,
    @ColumnInfo val cost: Int,
    @ColumnInfo val suitableForChildren: Int
)