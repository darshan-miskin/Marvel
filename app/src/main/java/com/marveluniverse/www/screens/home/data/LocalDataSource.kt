package com.marveluniverse.www.screens.home.data

import androidx.room.withTransaction
import com.marveluniverse.www.database.AppDatabase
import com.marveluniverse.www.database.CharactersDao
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: AppDatabase
) {
    private val charactersDao = database.charactersDao()

    suspend fun withTransaction(transaction: suspend () -> Unit ) = database.withTransaction { transaction }

    suspend fun insert(list: List<CharacterModel>) = charactersDao.insert(list)

    suspend fun clearAll() = charactersDao.clearAll()

    fun charactersPagingSource() = charactersDao.charactersPagingSource()
}