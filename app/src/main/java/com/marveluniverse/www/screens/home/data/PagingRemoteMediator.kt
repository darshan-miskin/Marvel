package com.marveluniverse.www.screens.home.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import okio.IOException
import timber.log.Timber
import javax.inject.Inject

const val LIMIT = 60
var remoteOffset = 0

@OptIn(ExperimentalPagingApi::class)
class PagingRemoteMediator @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    RemoteMediator<Int, CharacterModel>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterModel>
    ): MediatorResult {
        val lastItem = state.lastItemOrNull()

        Timber.tag("remote_mediator").d("loadType: $loadType \t lastItem: ${state.lastItemOrNull()?.name} \t " +
                "firstItem: ${state.firstItemOrNull()?.name}")

        val offset = when(loadType){
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                if(lastItem==null)
                    return MediatorResult.Success(endOfPaginationReached = false)
                else {
                    remoteOffset
                }
            }
        }
        try{
            val response = remoteDataSource.getCharactersPaging(LIMIT, offset)
            val result = response.data.results
            localDataSource.insert(result)
            remoteOffset = response.data.offset + response.data.count

            Timber.tag("remote_mediator").d("limit: $LIMIT \t offset: $offset")

            return MediatorResult.Success(endOfPaginationReached = result.isEmpty())
        }
        catch (e: Exception) {
            return MediatorResult.Error(IOException(e.message))
        }
    }

}