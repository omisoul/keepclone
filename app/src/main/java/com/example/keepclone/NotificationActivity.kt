package com.example.keepclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationsActivity : AppCompatActivity() {

    private lateinit var rvNotifications: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        rvNotifications = findViewById(R.id.rvNotifications)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val notificationsList = mutableListOf(
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


    }


}