package com.marveluniverse.www.screens.details.domain

import android.util.Log
import com.marveluniverse.www.networking.retrofit.MarvelApi
import com.marveluniverse.www.screens.common.domain.response.Result
import com.marveluniverse.www.screens.common.TAG_API
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetComicsUseCase @Inject constructor(private val marvelApi: MarvelApi) {
    private val limit=20
    private var offSet=-limit

    suspend fun getComics() = withContext(Dispatchers.IO){
        offSet+=limit
        val response = marvelApi.getComics("123456", limit, offSet)
        Log.d(TAG_API, "api call made")
        return@withContext if (response.isSuccessful && response.body() != null) {
            Log.d(TAG_API, "api response ${response.body()}")
            Result.Success(response.body()!!.data.results)
        } else
            Result.Failure
    }
}