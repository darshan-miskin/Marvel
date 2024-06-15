package com.marveluniverse.www.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<CharacterModel>): List<Long>

    @Query("select * from charactermodel order by name limit :limit offset :offSet")
    suspend fun fetchCharacters(limit: Int, offSet:Int): List<CharacterModel>

    @Query("select * from charactermodel order by name COLLATE NOCASE ASC")
    fun charactersPagingSource() : PagingSource<Int, CharacterModel>
}