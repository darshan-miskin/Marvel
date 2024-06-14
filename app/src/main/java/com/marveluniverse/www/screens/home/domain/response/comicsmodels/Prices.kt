package com.marveluniverse.www.screens.home.domain.response.comicsmodels

import com.google.gson.annotations.SerializedName


data class Prices(

    @SerializedName("type") var type: String? = null,
    @SerializedName("price") var price: String? = null

)