package com.example.coordinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val membersbtn = findViewById<Button>(R.id.editmembersbtn)
        membersbtn.setOnClickListener {
            val intent = Intent(this, EditMembers::class.java)
            startActivity(intent)
        }

        val viewbtn = findViewById<Button>(R.id.viewbtn)
        viewbtn.setOnClickListener {
            val intent = Intent(this, View::class.java)
            startActivity(intent)
        }

    }
}