package com.example.keepclone

import androidx.room.Entity
import androidx.room.PrimaryKey


import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val type: String,
    val info: String,
    val time: String)

class Notifications{





    }







