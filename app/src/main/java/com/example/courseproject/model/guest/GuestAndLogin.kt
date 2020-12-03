package com.example.courseproject.model.guest

import androidx.room.Embedded
import androidx.room.Relation
import com.example.courseproject.model.login.LoginEntity

data class GuestAndLogin(
    @Embedded val loginEntity: LoginEntity,
    @Relation(
        parentColumn = "loginID",
        entityColumn = "guestLoginID"
    )
    val guestEntity: GuestEntity
)
