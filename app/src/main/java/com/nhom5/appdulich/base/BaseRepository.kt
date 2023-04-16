package com.nhom5.appdulich.base

import com.nhom5.appdulich.base.response.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {
    suspend fun <T> callData(api: suspend ()->T) : DataResponse<T> {
        return withContext(Dispatchers.IO){
            try {
                DataResponse.Success(api.invoke())
            }catch (throwable : Throwable){
                DataResponse.Fail(throwable)
            }
        }
    }
}