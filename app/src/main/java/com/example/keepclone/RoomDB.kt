package com.example.keepclone

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Todo::class,Notification::class,SubtaskViewModel::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun todoDao(): TodoDao
    abstract fun notificationDao(): NotificationDao
    abstract fun subtaskDao(): SubtaskDao
}