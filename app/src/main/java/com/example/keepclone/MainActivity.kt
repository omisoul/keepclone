package com.example.keepclone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_task: FloatingActionButton = findViewById(R.id.floatingActionButton2)
        var notes:String
        var title:String
        var category:String
        var date:String //Format yyyy/mm/dd

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
    }
}






