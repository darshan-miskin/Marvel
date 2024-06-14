package com.marveluniverse.www.screens.home.domain.response.charactermodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Items(

    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("name") var name: String? = null

): Serializable