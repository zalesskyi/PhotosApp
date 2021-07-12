package com.zalesskyi.photosapp.navigation.di

import android.app.Activity
import androidx.navigation.NavController
import com.zalesskyi.photosapp.MainActivity
import com.zalesskyi.photosapp.navigation.AppNavComponentsNavigator
import com.zalesskyi.photosapp.navigation.AppNavProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {

    @Provides
    fun provideAppNavProvider(activity: Activity): AppNavProvider = (activity as MainActivity)

    @Provides
    fun provideNavController(provider: AppNavProvider): NavController = provider.getNavController()

    @Provides
    fun provideAppNavigator(navController: NavController): AppNavComponentsNavigator =
        AppNavComponentsNavigator(navController)
}