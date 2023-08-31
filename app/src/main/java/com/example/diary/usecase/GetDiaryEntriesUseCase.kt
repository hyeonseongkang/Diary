package com.example.diary.usecase

import androidx.lifecycle.LiveData
import com.example.diary.data.Diary

interface GetDiaryEntriesUseCase {
    fun execute(): LiveData<List<Diary>>
}