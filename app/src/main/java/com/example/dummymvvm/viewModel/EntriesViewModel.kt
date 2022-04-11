package com.example.dummymvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummymvvm.model.EntriesResponse
import com.example.dummymvvm.network.Resource
import com.example.dummymvvm.repository.EntryRepository
import kotlinx.coroutines.launch

class EntriesViewModel(private val repo: EntryRepository): ViewModel() {

    private var _entriesResponse: MutableLiveData<Resource<EntriesResponse>> = MutableLiveData()
    val entriesResponse: LiveData<Resource<EntriesResponse>> get() =_entriesResponse

    fun entries() = viewModelScope.launch {
        _entriesResponse.value = repo.entryData()
    }
}