package com.marveluniverse.www.screens.common.domain.response

import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import java.io.Serializable

data class BaseResponse<T: BaseResult>(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: BaseData<T>
)

sealed class Result: Serializable{
    data class Success(val data: List<CharacterModel>) : Result(){
        inline fun <reified T> getResponse(): ArrayList<T>{
            return data as ArrayList<T>
        }
    }
    data object Failure : Result()

    data class Loading(val state: State) : Result()
}
enum class State {
    REFRESH, LIST_APPEND
}