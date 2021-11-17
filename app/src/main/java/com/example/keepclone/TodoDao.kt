package com.example.keepclone

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun loadAllTodo(): Array<Todo>

            //Check if LIKE is used properly here, Consider EQUALS if you can work it
    @Query("SELECT * FROM Todo WHERE id LIKE :id")
    fun getTodobyID(id : Int): Array<Todo>

    @Query("SELECT * FROM Todo WHERE title LIKE :title")
    fun getTodobyTitle(title: String): Array<Todo>

    @Query("SELECT * FROM Todo WHERE dueDate LIKE :date")
    fun getTodobyDueDate(date : String): Array<Todo>

    @Query("SELECT * FROM Todo WHERE category LIKE :category")
    fun getTodobyCategory(category : String): Array<Todo>

    @Query("SELECT * FROM Todo WHERE isComplete LIKE :status")
    fun getCompleteStatus(status : Boolean): Array<Todo>

    @Query("SELECT * FROM Todo ORDER BY id DESC LIMIT 1")
    fun getLastAdded():List<Todo>

    @Insert
    fun insertTodo(todoitem: Todo)

    @Delete
    fun delete(todoitem: Todo)

}