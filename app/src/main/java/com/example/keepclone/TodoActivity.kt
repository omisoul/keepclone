package com.example.keepclone

import SubtaskAdapter
import android.app.*
import android.os.Build
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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class TodoActivity: AppCompatActivity(),DatePickerDialog.OnDateSetListener  {
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
        //val mActivity = NotificationsActivity()

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
            notificate(title.text.toString())
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

    val channel_Id = "channelID"
    val channel_Name = "channelName"
    val notification_Id = 0


    fun notificate( title:String ){
        createNotificationChannel()

        val notification = NotificationCompat.Builder (this, channel_Id)
            .setContentTitle("Good day!")
            .setContentText(title + "is due today!")
            .setSmallIcon(R.drawable.ic_fi_bell)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)


        notificationManager.notify(notification_Id,notification)
    }



    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channel_Id, channel_Name,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = android.graphics.Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }



}