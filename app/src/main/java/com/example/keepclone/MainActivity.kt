package com.example.keepclone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

lateinit var sort:String
lateinit var db:RoomDB
var todoList:List<Todo> = emptyList()
lateinit var todoAdapter: TodoAdapter
class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_task: FloatingActionButton = findViewById(R.id.floatingActionButton2) //Format yyyy/mm/dd
        val todoRecyclerView:RecyclerView = findViewById(R.id.main_screen_recycler_view)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val nav_view: NavigationView = findViewById(R.id.nav_view)
        val menu_toggle: ImageButton = findViewById(R.id.menu_toggle)

        //Starting database in Main Activity
        db = Room.databaseBuilder(this,RoomDB::class.java, "todo_database").build()
        //Starting todoDao
        val todoDao = db.todoDao()

        val subtaskDao = db.subtaskDao()
        todoAdapter = TodoAdapter(todoList,db)


        todoRecyclerView.layoutManager = LinearLayoutManager(this)


        GlobalScope.launch{
            val a_todo = async (Dispatchers.IO) {loadTodos(db)}
            todoList = a_todo.await()
            todoAdapter.todos = todoList
            todoRecyclerView.adapter = todoAdapter
            todoAdapter.notifyDataSetChanged()
            removeBackground(todoList,todoRecyclerView)

        }






        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                //Data after user chooses to add a task
                var title = result.data?.getStringExtra("title").toString()
                var notes = result.data?.getStringExtra("notes").toString()
                var category = result.data?.getStringExtra("category").toString()
                var date = result.data?.getStringExtra("date").toString()
                var subtasks = result.data?.getStringArrayListExtra("subtasks")
                if (title.isNotBlank()){
                    var todo = Todo(title = title,category = category,dueDate = date,notes= notes,isComplete = false,0)

                    //Coroutine
                    GlobalScope.launch{
                        addNewItem(db,todo)
                        if (subtasks != null) {
                                addNewSubtasks(db,subtasks)
                        }
                        val newTodoList = async(Dispatchers.IO) { todoDao.loadAllTodo()}
                        todoAdapter.todos = newTodoList.await()


                    }
                    todoAdapter.notifyDataSetChanged()
                    removeBackground(todoAdapter.todos,todoRecyclerView)
                }else
                {
                    val toast:Toast = Toast.makeText(this,"Title is Empty. Note Canceled",Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            else{
                val toast:Toast = Toast.makeText(this,"Canceled Note",Toast.LENGTH_SHORT)
                toast.show()
            }




        }
        //Set FloatingActionButton Listener
        add_task.setOnClickListener{
            startForResult.launch(Intent(this,TodoActivity::class.java))
        }

        menu_toggle.setOnClickListener{
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        nav_view.setNavigationItemSelectedListener(this)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notifications, menu)

        val notificationBtn = menu?.findItem(R.id.notification_bell)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.notification_bell-> {
                Intent(this, NotificationsActivity::class.java).also{
                    startActivity(it)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val sort = item.title
        if (sort == "Work" || sort == "Personal" || sort == "School"){
            sortByCategory(sort as String,db, todoAdapter)
        }
        else if (sort == "Today"){
            sortbyDate(sort as String,db, todoAdapter)
        }
        else if (sort == "All"){
            GlobalScope.launch {
                val a_todo = async (Dispatchers.IO) {loadTodos(db)}
                todoList = a_todo.await()
                todoAdapter.todos = todoList
                todoAdapter.notifyDataSetChanged()
            }

        }
/*        else if (sort == "Complete"){
            sortByCompleated(db, todoAdapter)
        }*/
        return true
    }

    private fun sortByCompleated(db: RoomDB,adapter: TodoAdapter) {
        GlobalScope.launch {

            val todos = async (Dispatchers.IO){ getTodoByStatus(db) }

            adapter.todos = todos.await()
            adapter.notifyDataSetChanged()
        }
    }

    private fun sortbyDate(date: String, db: RoomDB, adapter: TodoAdapter) {
        GlobalScope.launch {

            val todos = async (Dispatchers.IO){ getTodoByDate(db) }

            adapter.todos = todos.await()
            adapter.notifyDataSetChanged()
        }
    }


    private fun loadTodos(db: RoomDB):List<Todo>{


        return db.todoDao().loadAllTodo()
    }

    private fun addNewItem(db:RoomDB,todo:Todo){
        db.todoDao().insertTodo(todo)


    }

    private fun addNewSubtasks(db: RoomDB, subtasks: ArrayList<String>){
        val lastAdded = db.todoDao().getLastAdded()
        for (subtask in subtasks){
            db.subtaskDao().insertSubTask(SubtaskViewModel(subtask,lastAdded[0].id,0))
        }
    }

    private fun removeBackground(todoList:List<Todo>,todoRecyclerView:RecyclerView){
        if (todoList.isNotEmpty()) {
            todoRecyclerView.visibility = View.VISIBLE
            val backgroundImage: ImageView = findViewById(R.id.imageView2)
            val text1: TextView = findViewById(R.id.textView2)
            val text2: TextView = findViewById(R.id.textView3)
            val text3: TextView = findViewById(R.id.textView4)
            backgroundImage.visibility = View.GONE
            text1.visibility = View.GONE
            text2.visibility = View.GONE
            text3.visibility = View.GONE

        }
    }
    fun sortByCategory(category: String,db:RoomDB,adapter: TodoAdapter){
        GlobalScope.launch {

            val todos = async (Dispatchers.IO){ getTodoByCategory(category,db) }

            adapter.todos = todos.await()
            adapter.notifyDataSetChanged()
        }
    }

    fun getTodoByCategory(category: String,db: RoomDB):List<Todo>{
        val _todos = db.todoDao().getTodobyCategory(category)
        return _todos
    }

    fun getTodoByDate(db: RoomDB):List<Todo>{
        var date:String = ""
        val calendar: Calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        date = "Selected $year/${month+1}/$day"
        val _todos = db.todoDao().getTodobyDueDate(date)
        println(_todos)
        return _todos
    }

    fun getTodoByStatus(db: RoomDB):List<Todo>{
        return db.todoDao().getCompleteStatus(true)
    }
}







