package com.example.keepclone

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationsActivity : AppCompatActivity() {

    private lateinit var rvNotifications: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        rvNotifications = findViewById(R.id.rvNotifications)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)}


        // This commenting out old dummy data Look at notification data base
     /*   val notificationsList = mutableListOf(
            Notification("Upcoming","Hey Jane Doe, you have two upcoming tasks. Tap here to view them.", "9:30PM"),
            Notification("Overdue","Hey Jane Doe, you have two task overdue. ", "9:20PM"),
            Notification("Upcoming","Hey Jane Doe, you have two upcoming tasks. Tap here to view them.", "9:30PM"),
            Notification("Overdue","Hey Jane Doe, you have two task overdue. ", "9:20PM"),
            Notification("Upcoming","Hey Jane Doe, you have two upcoming tasks. Tap here to view them.", "9:30PM"),
            Notification("Overdue","Hey Jane Doe, you have two task overdue. ", "9:20PM"),
            Notification("Upcoming","Hey Jane Doe, you have two upcoming tasks. Tap here to view them.", "9:30PM"),
            Notification("Overdue","Hey Jane Doe, you have two task overdue. ", "9:20PM")
        )

        val adapter = NotificationAdapter(notificationsList)
        rvNotifications.adapter = adapter
        rvNotifications.layoutManager = LinearLayoutManager(this)

        //

        notificate()
    }
    val channel_Id = "channelID"
    val channel_Name = "channelName"
    val notification_Id = 0


    fun notificate(){
        createNotificationChannel()

        val notification = NotificationCompat.Builder (this, channel_Id)
            .setContentTitle("Awesome Notification")
            .setContentText("This is the context of the text")
            .setSmallIcon(R.drawable.ic_fi_bell)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)


        notificationManager.notify(notification_Id,notification)
    }

    

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channel_Id, channel_Name,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = android.graphics.Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
*/
}