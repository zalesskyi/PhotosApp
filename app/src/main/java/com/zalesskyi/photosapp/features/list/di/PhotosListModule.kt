package com.zalesskyi.photosapp.features.list.di

import com.zalesskyi.photosapp.features.list.PhotosListViewModel
import com.zalesskyi.photosapp.features.list.PhotosListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class PhotosListModule {

    @Binds
    abstract fun bindPhotosListViewModel(viewModel: PhotosListViewModelImpl): PhotosListViewModel
}