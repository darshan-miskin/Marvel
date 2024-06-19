package com.marveluniverse.www.screens.common.domain.response

data class BaseResponse<T: BaseResult>(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: BaseData<T>
)

