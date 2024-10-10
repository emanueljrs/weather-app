package com.emanuel.weatherapp.data.remote.di

import com.emanuel.weatherapp.data.remote.datasource.RemoteDataSource
import com.emanuel.weatherapp.data.remote.datasource.RetrofitRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

    @Binds
    fun bindRetrofitRemoteDataSource(remoteDataSource: RetrofitRemoteDataSource): RemoteDataSource
}