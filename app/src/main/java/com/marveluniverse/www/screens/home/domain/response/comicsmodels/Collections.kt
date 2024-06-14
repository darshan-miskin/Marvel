package com.marveluniverse.www.screens.home.domain.response.comicsmodels

import com.google.gson.annotations.SerializedName


data class Collections(

    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("name") var name: String? = null

)