package com.marveluniverse.www.screens.home.domain.response.comicsmodels

import com.google.gson.annotations.SerializedName


data class Creators(

    @SerializedName("available") var available: String? = null,
    @SerializedName("returned") var returned: String? = null,
    @SerializedName("collectionURI") var collectionURI: String? = null,
    @SerializedName("items") var items: ArrayList<com.marveluniverse.www.screens.home.domain.response.comicsmodels.Items> = arrayListOf()

)