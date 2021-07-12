package com.zalesskyi.photosapp.navigation

import androidx.navigation.fragment.FragmentNavigator
import com.zalesskyi.domain.models.Photo
import com.zalesskyi.photosapp.features.list.PhotosListFragmentDirections
import javax.inject.Inject

interface MainNavigator {

    fun toPhotoDetail(photo: Photo, extras: FragmentNavigator.Extras)
}

class MainNavigatorImpl
@Inject
constructor(private val navigator: Navigator) : MainNavigator {

    override fun toPhotoDetail(photo: Photo, extras: FragmentNavigator.Extras) {
        navigator.navigate(NavigationCommand.ToDirections(
            PhotosListFragmentDirections.actionPhotosFragmentToPhotoFragment(photo)
        ), extras)
    }
}