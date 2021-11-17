package com.example.keepclone

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Todo::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("todoID"),
    onDelete = ForeignKey.CASCADE)
))
data class SubtaskViewModel (
    val text: String,
    val todoID:Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int

    )