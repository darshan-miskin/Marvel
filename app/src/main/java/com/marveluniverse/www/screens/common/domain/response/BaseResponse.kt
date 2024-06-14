package com.marveluniverse.www.screens.common.domain.response

data class BaseResponse<T: BaseResult>(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: BaseData<T>
)

sealed class Result{
    data class Success(val response: ArrayList<out BaseResult>) : Result()
    data object Failure : Result()
    data class Loading(val state: State) : Result()
}
enum class State {
    REFRESH, LIST_APPEND
}