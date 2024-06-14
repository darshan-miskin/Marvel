package com.marveluniverse.www.screens.home.domain.response.charactermodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Urls(

    @SerializedName("type") var type: String? = null,
    @SerializedName("url") var url: String? = null

):Serializable