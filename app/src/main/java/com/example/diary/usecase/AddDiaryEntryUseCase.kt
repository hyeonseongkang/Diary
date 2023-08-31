package com.example.diary.usecase

import com.example.diary.data.Diary

interface AddDiaryEntryUseCase {
    fun execute(entry: Diary)
}