package com.example.dummymvvm.network

import com.example.dummymvvm.model.EntriesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("entries")
    suspend fun entries(): EntriesResponse
}