package com.zalesskyi.data.repositories.di

import com.zalesskyi.data.repositories.MediaRepositoryImpl
import com.zalesskyi.domain.repositories.MediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMediaRepository(repository: MediaRepositoryImpl): MediaRepository
}