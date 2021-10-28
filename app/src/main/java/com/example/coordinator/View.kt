package com.example.coordinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.database.*

class View : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        

        database = FirebaseDatabase.getInstance().getReference()
        var memberlist : MutableList<Member> = ArrayList()

        // Listener to get the table of members
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    val firstname = child.child("fname").getValue().toString()
                    val lastname = child.child("lname").getValue().toString()
                    val groupnum = child.child("group").getValue().toString()
                    val newmember = Member(firstname, lastname, groupnum)
                    memberlist.add(newmember)
                }
                showTable(memberlist)
            }
            override fun onCancelled(error: DatabaseError) {
                println("ERROR: $error")
            }

        }
        database.child("Members").addValueEventListener(listener)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val membername = findViewById<EditText>(R.id.nameinput)
            val timelist : MutableList<Timeslot> = ArrayList()


            // Listener to get the timeslot info
            val timelistener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (child in snapshot.children) {
                        if (child.child("member").getValue().toString() == membername.text.toString()) {
                            val id = child.child("id").getValue().toString()
                            val weekday = child.child("weekday").getValue().toString()
                            val time = child.child("time").getValue().toString()
                            val member = child.child("member").getValue().toString()
                            val newtime = Timeslot(id, weekday, time, member)
                            timelist.add(newtime)
                            println("New timeslot: $newtime")
                        }
                    }
                    showSchedule(timelist)
                }
                override fun onCancelled(error: DatabaseError) {
                    println("ERROR: $error")
                }

            }
            database.child("Times").addValueEventListener(timelistener)
        }

        val homebtn = findViewById<Button>(R.id.homebtn)
        homebtn.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

    }

    //displays the member table
    private fun showTable(list : MutableList<Member>) {
        val fcol = findViewById<TextView>(R.id.fnamecol)
        val lcol = findViewById<TextView>(R.id.lnamecol)
        val gcol = findViewById<TextView>(R.id.groupcol)
        for (member in list) {
            fcol.text = ("${fcol.text}\n${member.fname}")
            lcol.text = ("${lcol.text}\n${member.lname}")
            gcol.text = ("${gcol.text}\n${member.group}")
        }
    }

    // Displays the schedule for a member
    private fun showSchedule(list : MutableList<Timeslot>) {
        val dayCol = findViewById<TextView>(R.id.weekdaycol)
        val timeCol = findViewById<TextView>(R.id.timecol)

        dayCol.text = "DAY"
        timeCol.text = "TIME"

        if (list.isEmpty()) {
            dayCol.text = "No Available Times"
            timeCol.text = ""
        }

        for (time in list) {
            dayCol.text = ("${dayCol.text}\n${time.weekday}")
            timeCol.text = ("${timeCol.text}\n${time.time}")
        }
    }
}