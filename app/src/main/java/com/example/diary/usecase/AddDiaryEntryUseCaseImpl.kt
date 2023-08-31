package com.example.diary.usecase

class AddDiaryEntryUseCaseImpl() : AddDiaryEntryUseCase {
    override fun execute(entry: DiaryEntry) {
        interactor.addDiaryEntry(entry)
    }
}