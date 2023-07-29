package com.example.diary.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diary.R
import com.example.diary.databinding.ActivityCreateDiaryBinding
import com.google.android.material.snackbar.Snackbar
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

        binding.btnSave.setOnClickListener {
            saveDiary()
        }
    }

    private fun saveDiary() {
        val diary = binding.tvDiary.text.toString().trim()

        if (diary.isNotEmpty()) {
            val diaryRef = myRef.push()
            diaryRef.setValue(diary)
                .addOnSuccessListener {
                    // 일기 저장 성공 처리
                    showSnackbar("일기가 저장되었습니다.")
                }
                .addOnFailureListener {
                    // 일기 저장 실패 처리
                    showSnackbar("일기 저장에 실패하였습니다.")
                }
        } else {
            // 일기 내용이 비어있는 경우
            showSnackbar("일기 내용을 입력해주세요.")
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}