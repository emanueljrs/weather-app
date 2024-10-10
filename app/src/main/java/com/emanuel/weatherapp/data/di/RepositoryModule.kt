package com.emanuel.weatherapp.data.di

import com.emanuel.weatherapp.domain.repository.WeatherRepository
import com.emanuel.weatherapp.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindWeatherRepository(bind: WeatherRepositoryImpl): WeatherRepository
}