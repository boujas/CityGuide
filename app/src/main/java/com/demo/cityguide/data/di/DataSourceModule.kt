package com.demo.cityguide.data.di

import com.demo.cityguide.data.datasource.local.LocalDataSource
import com.demo.cityguide.data.datasource.local.LocalDataSourceImpl
import com.demo.cityguide.data.datasource.remote.RemoteDataSource
import com.demo.cityguide.data.datasource.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        impl: LocalDataSourceImpl
    ): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        impl: RemoteDataSourceImpl
    ): RemoteDataSource

}