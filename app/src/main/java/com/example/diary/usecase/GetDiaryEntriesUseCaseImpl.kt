package com.example.diary.usecase

import androidx.lifecycle.LiveData
import com.example.diary.data.Diary

class GetDiaryEntriesUseCaseImpl() : GetDiaryEntriesUseCase {
    override fun execute(): LiveData<List<Diary>> {
    }
}