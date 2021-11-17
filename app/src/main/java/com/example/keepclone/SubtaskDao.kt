package com.example.keepclone
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Dao
import androidx.room.Query


@Dao
interface SubtaskDao {
    @Query("SELECT * FROM SubtaskViewModel")
    fun loadAllSubtask(): Array<SubtaskViewModel>

//          //need id in SubtaskViewModel and Todo
//    @Query("SELECT * FROM SubtaskViewModel WHERE id LIKE :todoId")
//    fun getSubtaskbyTodoId(todoId: String): Notification

    @Insert
    fun insertSubTask(subtask: SubtaskViewModel)

    @Delete
    fun delete(subtask: SubtaskViewModel)

}