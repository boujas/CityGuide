package com.demo.cityguide.data.di

import com.demo.cityguide.data.datasource.remote.RemoteDataSource
import com.demo.cityguide.data.datasource.remote.RemoteDataSourceImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideRemoteDataSource(firestore: FirebaseFirestore): RemoteDataSource =
        RemoteDataSourceImpl(firestore)
}