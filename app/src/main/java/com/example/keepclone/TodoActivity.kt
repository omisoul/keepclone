package com.example.keepclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoActivity : AppCompatActivity() {

    private lateinit var rvTodos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        rvTodos = findViewById(R.id.rvTodos)


        val todosList = mutableListOf(
            Todo("Finish android dev designs", "Work", "9:30PM", "I'm a bit tired", false)
        )

//        val adapter = TodoAdapter(todosList)
//        rvTodos.adapter = adapter
//        rvTodos.layoutManager = LinearLayoutManager(this)
    }
}