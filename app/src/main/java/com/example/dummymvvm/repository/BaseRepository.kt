package com.example.dummymvvm.repository

import com.example.dummymvvm.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (e: Throwable) {
                when (e) {

                    is HttpException -> {
                        Resource.Error(false,""+e.code(),e.response()?.errorBody())
                    }

                    else ->{
                        Resource.Error(true,""+e.message,null)
                    }
                }
            }

        }
    }
}