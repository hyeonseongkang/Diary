package com.example.diary.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diary.data.Diary
import com.example.diary.databinding.FragmentHomeBinding
import com.example.diary.ui.activity.CreateDiaryActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val database = Firebase.database
    val myRef = database.getReference("diary")

    val diaryList: MutableList<Diary> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // onCreateView -> view를 생성하고 onViewCreated로 return함
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // onCreateView에서 생성한 view가 넘어옴, 여기서 view와 관련된 작업 수행
        btnClickListener()
    }

    private fun btnClickListener() {
        binding.btnCreateDiary.setOnClickListener {
            val intent = Intent(context, CreateDiaryActivity::class.java)
            startActivity(intent)
        }
    }

    fun getDiaryData() {
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (snapshot1 in snapshot.children) {
                    val diary: Diary? = snapshot1.getValue(Diary::class.java)
                    diaryList.add(diary!!)
                }
               // Log.d(TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
               // Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}