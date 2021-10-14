package com.example.keepclone

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class DisplayAddTaskActivity: AppCompatActivity(),DatePickerDialog.OnDateSetListener  {
    var date:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_todo)
        val datePickerButton:Button = findViewById(R.id.due_date_picker)
        val notes:EditText = findViewById(R.id.notes)
        val title:EditText = findViewById(R.id.add_todo_text)
        val cancel:Button = findViewById(R.id.cancel_button)
        val confirm:Button = findViewById(R.id.add_task_button)
        val category:Spinner = findViewById(R.id.category_spinner)




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


    }


}