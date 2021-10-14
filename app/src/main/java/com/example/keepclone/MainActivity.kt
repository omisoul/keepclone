package com.example.keepclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawer: NavigationView = findViewById(R.id.nav_view);
        val menu_button: ImageButton = findViewById(R.id.menu_button)

        drawer.visibility= View.INVISIBLE
        menu_button.setOnClickListener{
            drawer.visibility= View.VISIBLE
        }

//        val close_button: ImageButton = findViewById(R.id.close)
//        close_button.setOnClickListener{
//            drawer.visibility= View.INVISIBLE
//        }




    }
}