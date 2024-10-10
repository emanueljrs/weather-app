package com.emanuel.weatherapp.data.remote.di

import com.emanuel.weatherapp.data.remote.repository.RemoteRepository
import com.emanuel.weatherapp.data.remote.repository.WeatherRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteRepositoryModule {

    @Binds
    fun bindWeatherRemoteRepository(bind: WeatherRemoteRepository): RemoteRepository
}