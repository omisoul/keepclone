package com.example.keepclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
<<<<<<< HEAD
import android.widget.ImageButton
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
=======
import com.google.android.material.floatingactionbutton.FloatingActionButton

>>>>>>> 3654c449297927837e2ea56862f4d0759821c673

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< HEAD
        val drawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        val nav_view:NavigationView = findViewById(R.id.nav_view)
        val menu_toggle:ImageButton = findViewById(R.id.menu_toggle)

        // Button to open nav
        menu_toggle.setOnClickListener{
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        nav_view.setNavigationItemSelectedListener(this)
=======
        val add_task: FloatingActionButton = findViewById(R.id.floatingActionButton2)


        add_task.setOnClickListener {
            val intent = Intent(this, DisplayAddTaskActivity::class.java)
            startActivity(intent)
            val bundle: Bundle? = intent.extras
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notifications, menu)
>>>>>>> 3654c449297927837e2ea56862f4d0759821c673


<<<<<<< HEAD
=======
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.notification_bell-> {
                Intent(this, NotificationsActivity::class.java).also{
                    startActivity(it)
                }
                return true
>>>>>>> 3654c449297927837e2ea56862f4d0759821c673
            }
    // Get Selected Item
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(applicationContext,"${item.title.toString()} was selected",Toast.LENGTH_SHORT).show()
        return true
    }
<<<<<<< HEAD

}


=======
}







>>>>>>> 3654c449297927837e2ea56862f4d0759821c673
