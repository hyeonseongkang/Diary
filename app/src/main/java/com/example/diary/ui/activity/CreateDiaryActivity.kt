package com.example.diary.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diary.R
import com.example.diary.databinding.ActivityCreateDiaryBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateDiaryBinding

    val database = Firebase.database
    val myRef = database.getReference("diary")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDiaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    fun btnClick() {
        binding.btnSave.setOnClickListener {
            val diary = binding.tvDiary.text
            myRef.setValue(diary)
        }
    }
}