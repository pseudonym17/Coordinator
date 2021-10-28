package com.example.coordinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coordinator.databinding.ActivityEditMembersBinding
import com.example.coordinator.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditMembers : AppCompatActivity() {
    private lateinit var binding : ActivityEditMembersBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMembersBinding.inflate(layoutInflater)

        binding.addbtn.setOnClickListener {
            val fname: String = binding.fname.text.toString()
            val lname: String = binding.lname.text.toString()
            val group: String = binding.groupNum.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Members")
            val member = Member(fname, lname, group)

            database.child(fname).setValue(member).addOnSuccessListener {
                Toast.makeText(this@EditMembers, "Added Member", Toast.LENGTH_SHORT).show()
                binding.fname.text.clear()
                binding.lname.text.clear()
                binding.groupNum.text.clear()
            }.addOnFailureListener {
                Toast.makeText(this@EditMembers, "Failed to Add Member", Toast.LENGTH_SHORT).show()
            }
        }

        binding.removebtn.setOnClickListener {
            val fname: String = binding.fname.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Members")

            database.child(fname).getRef().removeValue().addOnSuccessListener {
                Toast.makeText(this@EditMembers, "Deleted Member", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this@EditMembers, "Failed to Delete Member", Toast.LENGTH_SHORT).show()
            }

            binding.fname.text.clear()
            binding.lname.text.clear()
            binding.groupNum.text.clear()
        }

        binding.editbtn.setOnClickListener {
            val fname: String = binding.fname.text.toString()
            val lname: String = binding.lname.text.toString()
            val group: String = binding.groupNum.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Members")
            val member = Member(fname, lname, group)

            database.child(fname).child("group").setValue(group).addOnSuccessListener {
                Toast.makeText(this@EditMembers, "Group Changed", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this@EditMembers, "Failed to Edit", Toast.LENGTH_SHORT).show()
            }

            binding.fname.text.clear()
            binding.lname.text.clear()
            binding.groupNum.text.clear()
        }

        binding.addtimebtn.setOnClickListener {
            val fname: String = binding.name.text.toString()
            val weekday: String = binding.weekday.text.toString()
            val time: String = binding.time.text.toString()
            val id = fname + weekday + time

            database = FirebaseDatabase.getInstance().getReference("Times")
            val timeslot = Timeslot(id, weekday, time, fname)

            database.child(id).setValue(timeslot).addOnSuccessListener {
                Toast.makeText(this@EditMembers, "Added Timeslot", Toast.LENGTH_SHORT).show()
                binding.name.text.clear()
                binding.weekday.text.clear()
                binding.time.text.clear()
            }.addOnFailureListener {
                Toast.makeText(this@EditMembers, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.homebtn.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}