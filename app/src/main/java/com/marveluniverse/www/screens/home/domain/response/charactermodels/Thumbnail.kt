package com.marveluniverse.www.screens.home.domain.response.charactermodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Thumbnail(

    @SerializedName("path") var path: String? = null,
    @SerializedName("extension") var extension: String? = null

): Serializable