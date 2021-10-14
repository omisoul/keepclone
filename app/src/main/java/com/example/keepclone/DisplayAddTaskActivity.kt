package com.example.keepclone

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DisplayAddTaskActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_todo)
        val notes:EditText = findViewById(R.id.notes)
        val title:EditText = findViewById(R.id.add_todo_text)
        val cancel:Button = findViewById(R.id.cancel_button)
        val confirm:Button = findViewById(R.id.add_task_button)
        val category_spinner:Spinner = findViewById(R.id.category_spinner)



        cancel.setOnClickListener {
            finish()
        }

    }



}