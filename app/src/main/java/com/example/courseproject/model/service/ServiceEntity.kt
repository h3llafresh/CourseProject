package com.example.courseproject.model.service

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "services")
data class ServiceEntity (
    @PrimaryKey(autoGenerate = true) val serviceID: Int = 0,

)