package com.marveluniverse.www.screens.common.domain

import com.marveluniverse.www.screens.common.domain.response.BaseResult

sealed class Result{
    data class Success(val response: ArrayList<out BaseResult>) : Result()
    data object Failure : Result()
    data object Loading : Result()
}