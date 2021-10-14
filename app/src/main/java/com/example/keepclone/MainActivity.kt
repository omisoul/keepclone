package com.example.keepclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val drawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        val nav_view:NavigationView = findViewById(R.id.nav_view)
        val menu_toggle:ImageButton = findViewById(R.id.menu_toggle)

        // Button to open nav
        menu_toggle.setOnClickListener{
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        nav_view.setNavigationItemSelectedListener(this)


            }
    // Get Selected Item
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(applicationContext,"${item.title.toString()} was selected",Toast.LENGTH_SHORT).show()
        return true
    }

}


