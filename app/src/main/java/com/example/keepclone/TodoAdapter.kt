package com.example.keepclone

import SubtaskAdapter
import android.app.Activity
import android.app.Dialog
import android.provider.Settings
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TodoAdapter(var todos: List<Todo>,db: RoomDB
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    lateinit var todo:Todo


    inner class TodoViewHolder(view: View): RecyclerView.ViewHolder(view){
        val info: TextView = view.findViewById(R.id.todo_info)
        val status: ImageView = view.findViewById(R.id.todo_status)
        val isComplete: CheckBox = view.findViewById(R.id.is_complete)
        val clickable: ConstraintLayout = view.findViewById(R.id.body)


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

        holder.clickable.setOnClickListener{

            GlobalScope.launch {
                val a_todo = async (Dispatchers.IO) {db.todoDao().getTodobyTitle(holder.info.text.toString())[0]}
                todo = a_todo.await()
            }
            val dialog = Dialog(holder.itemView.context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.view_todo)
            val date:TextView = dialog.findViewById(R.id.due_date)
            val notes:TextView = dialog.findViewById(R.id.notes)
            var title:TextView = dialog.findViewById(R.id.add_todo_text)
            val category:TextView = dialog.findViewById(R.id.category)
            val close:Button = dialog.findViewById(R.id.closeButton)

            date.text = todo.dueDate
            notes.text = todo.notes
            title.text = todo.title
            category.text = todo.category

            close.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()

        }


    }


    override fun getItemCount(): Int {
        return todos.size
    }
}
