package com.example.keepclone
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    val category: String,
    val dueDate: String,
    val notes: String,
    var isComplete: Boolean,
    @PrimaryKey(autoGenerate = true)
    var id: Int
)
