package com.example.keepclone

data class Todo(
    val title: String,
    val category: String,
    val dueDate: String,
    val notes: String,
    var isComplete: Boolean,
)