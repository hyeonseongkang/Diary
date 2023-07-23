package com.example.diary.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import com.example.diary.R
import com.example.diary.data.Diary
import com.example.diary.databinding.ActivityCreateDiaryBinding
import com.example.diary.databinding.ActivityEditDiaryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EditDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditDiaryBinding

    val database = Firebase.database
    val myRef = database.getReference("diary")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDiaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
}