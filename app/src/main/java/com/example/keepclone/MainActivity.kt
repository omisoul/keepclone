package com.example.keepclone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_task: FloatingActionButton = findViewById(R.id.floatingActionButton2)
        var notes:String
        var title:String
        var category:String
        var date:String //Format yyyy/mm/dd

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val nav_view: NavigationView = findViewById(R.id.nav_view)
        val menu_toggle: ImageButton = findViewById(R.id.menu_toggle)

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                //Data after user chooses to add a task
                title = result.data?.getStringExtra("title").toString()
                notes = result.data?.getStringExtra("notes").toString()
                category = result.data?.getStringExtra("category").toString()
                date = result.data?.getStringExtra("date").toString()
            }
            else{
                val toast:Toast = Toast.makeText(this,"Canceled Note",Toast.LENGTH_SHORT)
            }
        }
        //Set FloatingActionButton Listener
        add_task.setOnClickListener{
            startForResult.launch(Intent(this,DisplayAddTaskActivity::class.java))
        }

        menu_toggle.setOnClickListener{
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        nav_view.setNavigationItemSelectedListener(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notifications, menu)

        val notificationBtn = menu?.findItem(R.id.notification_bell)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.notification_bell-> {
                Intent(this, NotificationsActivity::class.java).also{
                    startActivity(it)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(applicationContext,"${item.title.toString()} was selected",Toast.LENGTH_SHORT).show()
        return true
    }
}







