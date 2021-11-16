package com.example.keepclone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val type: String,
    val info: String,
    val time: String
)