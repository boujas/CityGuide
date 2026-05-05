package com.demo.cityguide.data.di

import com.demo.cityguide.data.repository.PlacesRepositoryImpl
import com.demo.cityguide.domain.repository.PlacesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PlacesRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPlacesRepository(impl: PlacesRepositoryImpl): PlacesRepository

}