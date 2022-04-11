package com.example.dummymvvm.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dummymvvm.repository.BaseRepository
import com.example.dummymvvm.repository.EntryRepository
import com.example.dummymvvm.viewModel.EntriesViewModel
import java.lang.IllegalArgumentException

class MyFactory(private val repo: BaseRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(EntriesViewModel::class.java) -> EntriesViewModel(repo as EntryRepository) as T
            else -> throw IllegalArgumentException("VIEW MODEL CLASS NOT FOUND")
        }
    }
}