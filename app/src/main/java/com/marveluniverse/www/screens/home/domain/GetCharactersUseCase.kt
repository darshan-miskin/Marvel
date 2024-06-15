package com.marveluniverse.www.screens.home.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marveluniverse.www.screens.home.data.LocalDataSource
import com.marveluniverse.www.screens.home.data.PagingRemoteMediator
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetCharactersUseCase @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteMediator: PagingRemoteMediator
) {
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