package com.marveluniverse.www.screens.home.domain

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marveluniverse.www.screens.common.domain.response.Result
import com.marveluniverse.www.screens.common.TAG_API
import com.marveluniverse.www.screens.home.data.LocalDataSource
import com.marveluniverse.www.screens.home.data.PagingRemoteMediator
import com.marveluniverse.www.screens.home.data.RemoteDataSource
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetCharactersUseCase @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val remoteMediator: PagingRemoteMediator
) {
//    companion object {
//        val LIMIT = 100
//    }
//    private var offSet = 0
//
//    private var dbOffSet = 0
//
//    suspend fun getCharacters() = withContext(Dispatchers.IO) {
//        val dbList = localDataSource.fetchCharacters(20, dbOffSet)
//        if(dbList.isNotEmpty()) {
//            dbOffSet += dbList.size
//            return@withContext Result.Success(dbList as ArrayList)
//        }
//        else {
//            val response = remoteDataSource.getCharacters(LIMIT, offSet)
//            if (response.isSuccessful && response.body() != null) {
//                offSet += LIMIT
//                val insertCount = localDataSource.insert(
//                    response.body()!!.data.results.filter { characterModel: CharacterModel ->
//                        characterModel.isComplete
//                    }
//                ).size
//                val dbList = localDataSource.fetchCharacters(insertCount, dbOffSet)
//                dbOffSet += dbList.size
//                return@withContext Result.Success(dbList as ArrayList)
//            } else {
//                Log.d(TAG_API, "api call error")
//                return@withContext Result.Failure
//            }
//        }
//    }

    @OptIn(ExperimentalPagingApi::class)
    fun getCharactersPaging(): Flow<PagingData<CharacterModel>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { localDataSource.charactersPagingSource() }
        ).flow
    }
}