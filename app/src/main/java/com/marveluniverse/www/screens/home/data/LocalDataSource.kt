package com.marveluniverse.www.screens.home.data

import com.marveluniverse.www.database.CharactersDao
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val charactersDao: CharactersDao) {

    suspend fun insert(list: List<CharacterModel>) = charactersDao.insert(list)

    suspend fun fetchCharacters(limit: Int, offSet:Int) = charactersDao.fetchCharacters(limit, offSet)

    fun charactersPagingSource() = charactersDao.charactersPagingSource()
}