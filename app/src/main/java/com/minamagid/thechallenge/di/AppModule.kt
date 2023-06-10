package com.minamagid.thechallenge.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.minamagid.thechallenge.data.remote.Api
import com.minamagid.thechallenge.data.repository.RepositoryImpl
import com.minamagid.thechallenge.domain.repository.Repository
import com.minamagid.thechallenge.BuildConfig
import com.minamagid.thechallenge.common.Constants.BASE_URL
import com.minamagid.thechallenge.data.local.NYTimesDB
import com.minamagid.thechallenge.utils.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val timeOutMinutes = 1L

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request()
            .url
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client =
        if (BuildConfig.DEBUG) {
            OkHttpClient().newBuilder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .readTimeout(timeOutMinutes, TimeUnit.MINUTES)
                .connectTimeout(timeOutMinutes, TimeUnit.MINUTES)
                .writeTimeout(timeOutMinutes, TimeUnit.MINUTES)
                .build()
        } else {
            OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .readTimeout(timeOutMinutes, TimeUnit.MINUTES)
                .connectTimeout(timeOutMinutes, TimeUnit.MINUTES)
                .writeTimeout(timeOutMinutes, TimeUnit.MINUTES)
                .build()
        }

    @Provides
    @Singleton
    fun provideRetrofit(): Api {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: Api, NYTimesDB_Impl: NYTimesDB): Repository {
        return RepositoryImpl(api, db = NYTimesDB_Impl)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NYTimesDB {
        return Room.databaseBuilder(
            app,
            NYTimesDB::class.java,
            NYTimesDB.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    }

    @Singleton
    @Provides
    fun providesNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context)
    }


}