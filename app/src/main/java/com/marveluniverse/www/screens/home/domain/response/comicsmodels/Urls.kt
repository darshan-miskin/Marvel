package com.marveluniverse.www.screens.home.domain.response.comicsmodels

import com.google.gson.annotations.SerializedName


data class Urls(

    @SerializedName("type") var type: String? = null,
    @SerializedName("url") var url: String? = null

)