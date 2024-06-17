package com.marveluniverse.www.screens.home.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.marveluniverse.www.screens.common.TAG_DATA
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import okio.IOException
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
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

            //filter out data with no images and set empty description value
            val result = response.data.results.filter { it.isComplete }.onEach {
                if(it.description.isNullOrEmpty()) it.description = "Description Not Available"
            }

            localDataSource.withTransaction {
                if(loadType == LoadType.REFRESH){
                    localDataSource.clearAll()
                }
                localDataSource.insert(result)
            }
            remoteOffset = response.data.offset + response.data.count

            return MediatorResult.Success(endOfPaginationReached = response.data.count == response.data.total)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

}