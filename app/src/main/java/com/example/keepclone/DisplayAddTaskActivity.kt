package com.example.keepclone

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.time.Month
import java.util.*

class DisplayAddTaskActivity: AppCompatActivity(),DatePickerDialog.OnDateSetListener  {
    private lateinit var date:String
    private val datePickerButton:Button = findViewById(R.id.due_date_picker)
    private val notes:EditText = findViewById(R.id.notes)
    private val title:EditText = findViewById(R.id.add_todo_text)
    private val cancel:Button = findViewById(R.id.cancel_button)
    private val confirm:Button = findViewById(R.id.add_task_button)
    private val category:Spinner = findViewById(R.id.category_spinner)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_todo)
        cancel.setOnClickListener {
            finish()
        }
        confirm.setOnClickListener {
            intent.putExtra("title",title.text.toString())
            intent.putExtra("notes",notes.text.toString())
            intent.putExtra("category",category.selectedItem.toString())
            intent.putExtra("date",date)
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

    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayofMonth: Int) {
        date = "Date $year/$month/$dayofMonth"
        datePickerButton.text = date

    }


}