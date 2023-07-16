package com.example.diary.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.diary.R
import com.example.diary.databinding.FragmentHomeBinding
import com.example.diary.view.activity.CreateDiaryActivity

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}