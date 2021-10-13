package com.example.keepclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_task: FloatingActionButton = findViewById(R.id.floatingActionButton2)


        add_task.setOnClickListener {
            val intent = Intent(this, DisplayAddTaskActivity::class.java)
            startActivity(intent)
            val bundle: Bundle? = intent.extras
        }


    }
}





