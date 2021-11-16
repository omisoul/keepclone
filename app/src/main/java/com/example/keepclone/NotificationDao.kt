package com.example.keepclone
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationDao {
    @Query("SELECT * FROM Notification")
    fun loadAllNotifs(): Array<Notification>

    @Query("SELECT * FROM Notification WHERE time LIKE :date")
    fun getNotifbyDate(date: String): Notification

    @Query("SELECT * FROM Notification WHERE info LIKE :info")
    fun getInfo(info: String): Notification

    @Insert
    fun insertNotifs(notifs: Notification)

    @Delete
    fun delete(notifs: Notification)

}