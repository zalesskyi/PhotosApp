package com.zalesskyi.photosapp.navigation.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.NavigatorProvider
import com.zalesskyi.photosapp.MainActivity
import com.zalesskyi.photosapp.navigation.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationBindsModule {

    @Binds
    abstract fun bindNavigator(navigator: AppNavComponentsNavigator): Navigator

    @Binds
    abstract fun bindMainNavigator(navigator: MainNavigatorImpl): MainNavigator
}