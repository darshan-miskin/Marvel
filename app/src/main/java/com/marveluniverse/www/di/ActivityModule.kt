package com.marveluniverse.www.di

import com.marveluniverse.www.screens.home.data.LocalDataSource
import com.marveluniverse.www.screens.home.data.RemoteDataSource
import com.marveluniverse.www.screens.home.domain.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

//    @Provides
//    fun getCharactersUseCase(
//        localDataSource: LocalDataSource,
//         remoteDataSource: RemoteDataSource
//    ) = GetCharactersUseCase(localDataSource, remoteDataSource)
}