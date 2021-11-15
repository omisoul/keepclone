package com.example.keepclone

import SubtaskAdapter
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import android.widget.TextView
import android.view.Window

import android.widget.EditText










class DisplayAddTaskActivity: AppCompatActivity(),DatePickerDialog.OnDateSetListener  {
    private var date:String = ""
    private val calendar: Calendar = Calendar.getInstance()
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private val month = calendar.get(Calendar.MONTH)
    private val year = calendar.get(Calendar.YEAR)
    lateinit var datePickerButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_todo)
        var subtaskText: String
        val subtasks:ArrayList<SubtaskViewModel> = ArrayList()
        val button:Button = findViewById(R.id.due_date_picker)
        val notes:EditText = findViewById(R.id.notes)
        val title:EditText = findViewById(R.id.add_todo_text)
        val cancel:Button = findViewById(R.id.cancel_button)
        val confirm:Button = findViewById(R.id.add_task_button)
        val category:Spinner = findViewById(R.id.category_spinner)
        val addSubtask:ImageButton = findViewById(R.id.add_subtask)
        val subtaskRecycler:RecyclerView = findViewById(R.id.subtask_recycler)
        datePickerButton = button
        val adapter = SubtaskAdapter(subtasks)
        subtaskRecycler.layoutManager = LinearLayoutManager(this)
        subtaskRecycler.adapter = adapter

        date = "Selected $year/${month+1}/$day"
        datePickerButton.text = date

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
                final_subtask_array.add(i.text)
            }
            intent.putExtra("subtasks",final_subtask_array)
            setResult(Activity.RESULT_OK,intent)

            finish()
        }


        datePickerButton.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(this, this, year, month,day)
            datePickerDialog.show()



        }


        //On add subtask
        addSubtask.setOnClickListener(){
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.add_subtask)
            val body:EditText = dialog.findViewById(R.id.subtask_text_input)
            val yesBtn = dialog.findViewById(R.id.subtask_yes) as Button
            val noBtn = dialog.findViewById(R.id.subtask_cancel) as TextView
            yesBtn.setOnClickListener {
                body.text.toString().isBlank().apply {
                    dialog.dismiss()
                }
                Log.d("TAG", body.text.toString())
                Log.d("TAG", body.text.toString().isBlank().toString())
                subtasks.add(SubtaskViewModel(body.text.toString()))
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            noBtn.setOnClickListener { dialog.dismiss() }
            dialog.show()

        }



    }



    // On confirmed date picker selection
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayofMonth: Int) {

        date = "Selected $year/${month+1}/$dayofMonth"
        datePickerButton.text = date






    }



}