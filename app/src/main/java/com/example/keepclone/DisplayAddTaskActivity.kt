package com.example.keepclone

import SubtaskAdapter
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class DisplayAddTaskActivity: AppCompatActivity(),DatePickerDialog.OnDateSetListener  {
    var date:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_todo)
        val subtasks:ArrayList<SubtaskViewModel> = ArrayList()
        val datePickerButton:Button = findViewById(R.id.due_date_picker)
        val notes:EditText = findViewById(R.id.notes)
        val title:EditText = findViewById(R.id.add_todo_text)
        val cancel:Button = findViewById(R.id.cancel_button)
        val confirm:Button = findViewById(R.id.add_task_button)
        val category:Spinner = findViewById(R.id.category_spinner)
        val addSubtask:ImageButton = findViewById(R.id.add_subtask)
        val subtaskRecycler:RecyclerView = findViewById(R.id.subtask_recycler)

        val adapter = SubtaskAdapter(subtasks)
        subtaskRecycler.layoutManager = LinearLayoutManager(this)
        subtaskRecycler.adapter = adapter

        //On cancel
        cancel.setOnClickListener {
            finish()
        }

        //On add task
        confirm.setOnClickListener {
            intent.putExtra("title",title.text.toString())
            intent.putExtra("notes",notes.text.toString())
            intent.putExtra("category",category.selectedItem.toString())
            intent.putExtra("date",date)
            val final_subtask_array:ArrayList<String> = ArrayList()
            for (i in subtasks){
                final_subtask_array.add(i.task.text.toString())
            }
            intent.putExtra("subtasks",final_subtask_array)
            setResult(Activity.RESULT_OK,intent)

            finish()
        }

        datePickerButton.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this, this, year, month,day)
            datePickerDialog.show()


        }


        //On add subtask
        addSubtask.setOnClickListener(){
            subtasks.add(SubtaskViewModel())
            adapter.notifyDataSetChanged()
        }

    }



    // On confirmed date picker selection
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayofMonth: Int) {
        date = "Date $year/$month/$dayofMonth"


    }


}