package com.example.keepclone

import androidx.room.Entity

@Entity
data class Notification(
    val type: String,
    val info: String,
    val time: String
)