package com.example.dummymvvm.repository

import com.example.dummymvvm.network.ApiService
import com.example.dummymvvm.repository.BaseRepository

class EntryRepository(private val  api: ApiService): BaseRepository() {

    suspend fun entryData() = safeApiCall {
        api.entries()
    }
}