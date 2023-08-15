package com.example.diary.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import com.example.diary.R
import com.example.diary.data.Diary
import com.example.diary.databinding.ActivityCreateDiaryBinding
import com.example.diary.databinding.ActivityEditDiaryBinding
import com.example.diary.ui.custom.DrawView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EditDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditDiaryBinding
    private lateinit var drawView: DrawView

    val database = Firebase.database
    val myRef = database.getReference("diary")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDiaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonSave.setOnClickListener {  // Replace "saveButton" with your save button id in XML file
            saveDrawing()
        }

    }

    fun getMyDiary(id: String) {
        myRef.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val diary: Diary? = snapshot.getValue(Diary::class.java)
                val diaryText: Editable = diary?.diary?.let { SpannableStringBuilder(it) } ?: Editable.Factory.getInstance().newEditable("")
                binding.editTextContent.text = diaryText
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun getMyDiaryContainingWord(searchWord: String) {
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val matchingDiaries = mutableListOf<Diary>()

                for (diarySnapshot in snapshot.children) {
                    val diary: Diary? = diarySnapshot.getValue(Diary::class.java)
                    diary?.let {
                        if (it.diary.contains(searchWord)) {
                            matchingDiaries.add(it)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun getAllDiaries() {
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allDiaries = mutableListOf<Diary>()

                for (diarySnapshot in snapshot.children) {
                    val diary: Diary? = diarySnapshot.getValue(Diary::class.java)
                    diary?.let {
                        allDiaries.add(it)
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun saveDrawing() {
        val drawingReference: DatabaseReference = myRef.child("drawings").push()
        val points = binding.drawView.getPoints() // Replace "drawView" with your DrawView id in XML file

        drawingReference.setValue(points).addOnSuccessListener {
            // Drawing saved successfully
        }.addOnFailureListener { e ->
            // Handle the error
            e.printStackTrace()
        }
    }
}