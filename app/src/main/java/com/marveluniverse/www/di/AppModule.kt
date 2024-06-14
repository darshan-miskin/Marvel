package com.marveluniverse.www.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.marveluniverse.www.BuildConfig
import com.marveluniverse.www.database.AppDatabase
import com.marveluniverse.www.database.CharacterTypeConverter
import com.marveluniverse.www.networking.cryptography.md5
import com.marveluniverse.www.networking.retrofit.MarvelApi
import com.marveluniverse.www.screens.common.TAG_API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.Calendar
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun httpClient(): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor = Interceptor { chain ->
            val oldRequest = chain.request()

            val ts = Calendar.getInstance().timeInMillis
            val newUrl = oldRequest.url.newBuilder().addQueryParameter("ts", ts.toString())
                .addQueryParameter("hash", md5(ts))
                .addQueryParameter("apikey", BuildConfig.API_KEY)
                .build()

            val newRequest = oldRequest.newBuilder().url(newUrl).build()

            Timber.tag(TAG_API).d("request.url= ${newRequest.url}")
            return@Interceptor chain.proceed(newRequest)
        })
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY)})
        .build()

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun marvelApiService(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)

    @Provides
    @Singleton
    fun appDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "Marvel_Db")
            .build()

    @Singleton
    @Provides
    fun characterDao(appDatabase: AppDatabase) = appDatabase.charactersDao()
}