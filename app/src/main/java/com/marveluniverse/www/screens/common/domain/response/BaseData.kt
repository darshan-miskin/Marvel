package com.marveluniverse.www.screens.common.domain.response

data class BaseData<T: BaseResult>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<T>
)
