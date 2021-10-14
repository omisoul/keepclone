package com.example.keepclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notifications, menu)

        val notificationBtn = menu?.findItem(R.id.notification_bell)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.notification_bell-> {
                Intent(this, Notifications::class.java).also{
                    startActivity(it)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}