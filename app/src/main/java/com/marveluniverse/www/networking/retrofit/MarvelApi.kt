package com.marveluniverse.www.networking.retrofit

import com.marveluniverse.www.BuildConfig
import com.marveluniverse.www.screens.common.domain.response.BaseResponse
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offSet: Int
    ): Response<BaseResponse<CharacterModel>>

    @GET("/v1/public/characters")
    suspend fun getCharactersPaging(
        @Query("limit") limit: Int,
        @Query("offset") offSet: Int
    ): BaseResponse<CharacterModel>

    @GET("/v1/public/characters/{characterId}/comics&apikey=${BuildConfig.API_KEY}")
    suspend fun getComics(
        @Path("characterId") characterId: String,
        @Query("limit") limit: Int,
        @Query("offset") offSet: Int
    ): Response<BaseResponse<CharacterModel>>
}