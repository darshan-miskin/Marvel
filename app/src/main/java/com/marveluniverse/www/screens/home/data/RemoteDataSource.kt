package com.marveluniverse.www.screens.home.data

import com.marveluniverse.www.networking.retrofit.MarvelApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val marvelApi: MarvelApi) {

    suspend fun getCharacters(limit: Int, offSet: Int) = marvelApi.getCharacters(limit, offSet)

    suspend fun getCharactersPaging(limit: Int, offSet: Int) =
        marvelApi.getCharactersPaging(limit, offSet)
}