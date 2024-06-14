package com.marveluniverse.www.screens.home.domain.response.charactermodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Comics(

    @SerializedName("available") var available: Int? = null,
    @SerializedName("collectionURI") var collectionURI: String? = null,
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf(),
    @SerializedName("returned") var returned: Int? = null

): Serializable