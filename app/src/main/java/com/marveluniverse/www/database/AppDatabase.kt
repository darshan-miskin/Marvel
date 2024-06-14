package com.marveluniverse.www.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel

@Database(entities = [CharacterModel::class], version = 1)
@TypeConverters(value = [CharacterTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}