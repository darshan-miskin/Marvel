package com.marveluniverse.www.screens.home.domain

import com.marveluniverse.www.screens.common.domain.response.Result
import com.marveluniverse.www.screens.common.TAG_API
import com.marveluniverse.www.screens.home.data.LocalDataSource
import com.marveluniverse.www.screens.home.data.RemoteDataSource
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@ViewModelScoped
class GetCharactersUseCase @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    companion object {
        const val LIMIT = 100
    }
    //separate offset for local & remote because, data is inserted after filtering
    private var offSet = 0
    private var dbOffSet = 0

    suspend fun getCharacters() = withContext(Dispatchers.IO) {
        val dbList = localDataSource.fetchCharacters(40, dbOffSet)
        if(dbList.isNotEmpty()) {
            dbOffSet += dbList.size
            offSet+=dbList.size
            //db fetch is too fast, loader isn't visible without delay
            delay(500)
            return@withContext Result.Success(dbList)
        }
        else {
            try {
                val response = remoteDataSource.getCharacters(LIMIT, offSet)
                if (response.isSuccessful && response.body() != null) {
                    offSet += LIMIT
                    var result = response.body()!!.data.results
                    val names = result.map { it.name }
                    Timber.d(names.toString())

                    //filter out data which do not have image and if description is empty, set a string
                    result = result.filter { it.isComplete }.onEach {
                        it.description =
                            if (it.description.isNullOrEmpty()) "Description Not Available"
                            else it.description
                    } as ArrayList<CharacterModel>

                    val insertCount = localDataSource.insert(result).size
                    val dbListUpdated = localDataSource.fetchCharacters(insertCount, dbOffSet)
                    dbOffSet += dbListUpdated.size
                    return@withContext Result.Success(dbListUpdated)
                } else {
                    Timber.tag(TAG_API).d("api call error: ${response.errorBody().toString()}")
                    return@withContext Result.Failure
                }
            }catch (e: Exception){
                Timber.tag(TAG_API).d("api call error: ${e.stackTrace}")
                return@withContext Result.Failure
            }
        }
    }
}