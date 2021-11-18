package com.example.keepclone

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo Where isComplete is 0")
    fun loadAllTodo(): List<Todo>

            //Check if LIKE is used properly here, Consider EQUALS if you can work it
    @Query("SELECT * FROM Todo WHERE id LIKE :id")
    fun getTodobyID(id : Int): Array<Todo>

    @Query("SELECT * FROM Todo WHERE title LIKE :title")
    fun getTodobyTitle(title: String): Array<Todo>

    @Query("SELECT * FROM Todo WHERE dueDate LIKE :date And isComplete is 0")
    fun getTodobyDueDate(date : String): List<Todo>

    @Query("SELECT * FROM Todo WHERE category LIKE :category And isComplete is 0")
    fun getTodobyCategory(category : String): List<Todo>

    @Query("SELECT * FROM Todo WHERE isComplete LIKE :status")
    fun getCompleteStatus(status : Boolean): List<Todo>

    @Query("Update Todo SET isComplete = :status Where title Like :title")
    fun setCompleteStatus(title : String,status:Boolean)

    @Query("SELECT * FROM Todo ORDER BY id DESC LIMIT 1")
    fun getLastAdded():List<Todo>

    @Query("SELECT * FROM Todo Where dueDate < :date And isComplete is 0" )
    fun getTodoByOverdue(date: String):List<Todo>

    @Insert
    fun insertTodo(todoitem: Todo)

    @Delete
    fun delete(todoitem: Todo)

}