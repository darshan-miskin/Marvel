package com.marveluniverse.www.screens.home.domain.request

data class CharacterListRequest(val limit: Int, val offSet: Int){
    val name: String = ""
    val nameStartsWith = ""
    val modifiedSince = ""
    var comics = ""
    val series = ""
    val events = ""
    val stories = ""
    val orderBy = "" //either name or modified or -name or -modified. - symbol indicates descending order
}
