package com.example.keepclone

import android.app.Activity
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoAdapter(var todos: List<Todo>,db: RoomDB
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    inner class TodoViewHolder(view: View): RecyclerView.ViewHolder(view){
        val info: TextView = view.findViewById(R.id.todo_info)
        val status: ImageView = view.findViewById(R.id.todo_status)
        val isComplete: CheckBox = view.findViewById(R.id.is_complete)


    }

    override fun onCreateViewHolder(

        parent: ViewGroup,
        viewType: Int
    ): TodoAdapter.TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_con,parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TodoAdapter.TodoViewHolder,
        position: Int
    ) {
        holder.info.text = todos[position].title
        holder.isComplete.isChecked = todos[position].isComplete
        if(todos[position].category == "Work"){
            DrawableCompat.setTint(holder.status.drawable,ContextCompat.getColor(holder.status.context, R.color.primary_blue))
        }else{
            DrawableCompat.setTint(holder.status.drawable,ContextCompat.getColor(holder.status.context, R.color.green))
        }

        holder.isComplete.setOnCheckedChangeListener{ buttonView,isChecked ->
                println(isChecked)
                GlobalScope.launch {
                    db.todoDao().setCompleteStatus(holder.info.text as String,isChecked)
                }


        }


    }


    override fun getItemCount(): Int {
        return todos.size
    }
}
