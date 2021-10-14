package com.example.keepclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Notifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }
}