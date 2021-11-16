package com.example.keepclone

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_task: FloatingActionButton = findViewById(R.id.floatingActionButton2) //Format yyyy/mm/dd
        val todoRecyclerView:RecyclerView = findViewById(R.id.main_screen_recycler_view)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val nav_view: NavigationView = findViewById(R.id.nav_view)
        val menu_toggle: ImageButton = findViewById(R.id.menu_toggle)
        val todoList:ArrayList<Todo> = ArrayList()
        todoRecyclerView.layoutManager = LinearLayoutManager(this)
        val todoAdapter = TodoAdapter(todoList)
        todoRecyclerView.adapter = todoAdapter

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                //Data after user chooses to add a task
                var title = result.data?.getStringExtra("title").toString()
                var notes = result.data?.getStringExtra("notes").toString()
                var category = result.data?.getStringExtra("category").toString()
                var date = result.data?.getStringExtra("date").toString()
                if (title.isNotBlank()){
//                    @Brendon, work on this, "No value passed for parameter 'id'"
//                var todo = Todo(title = title,category = category,dueDate = date,notes= notes,isComplete = false)
                todoList.add(todo)
                    if (todoList.size > 0) {
                        todoRecyclerView.visibility = View.VISIBLE
                        val backgroundImage: ImageView = findViewById(R.id.imageView2)
                        val text1: TextView = findViewById(R.id.textView2)
                        val text2: TextView = findViewById(R.id.textView3)
                        val text3: TextView = findViewById(R.id.textView4)
                        backgroundImage.visibility = View.GONE
                        text1.visibility = View.GONE
                        text2.visibility = View.GONE
                        text3.visibility = View.GONE
                        todoAdapter.notifyDataSetChanged()
                    } else{
                        todoAdapter.notifyDataSetChanged()
                    }

                }else{
                val toast:Toast = Toast.makeText(this,"Title is Empty. Note Canceled",Toast.LENGTH_SHORT)
                    toast.show()
                }

            }
            else{
                val toast:Toast = Toast.makeText(this,"Canceled Note",Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        //Set FloatingActionButton Listener
        add_task.setOnClickListener{
            startForResult.launch(Intent(this,TodoActivity::class.java))
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







