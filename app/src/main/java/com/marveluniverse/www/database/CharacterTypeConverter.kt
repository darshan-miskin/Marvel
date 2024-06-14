package com.marveluniverse.www.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.marveluniverse.www.screens.home.domain.response.charactermodels.Comics
import com.marveluniverse.www.screens.home.domain.response.charactermodels.Thumbnail

class CharacterTypeConverter {

    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail) = Gson().toJson(thumbnail)

    @TypeConverter
    fun stringToThumbnail(string: String) = Gson().fromJson(string, Thumbnail::class.java)

    @TypeConverter
    fun comicsToString(comics: Comics) = Gson().toJson(comics)

    @TypeConverter
    fun stringToComics(string: String) = Gson().fromJson(string, Comics::class.java)
}