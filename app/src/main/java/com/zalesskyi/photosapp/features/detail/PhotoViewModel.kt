package com.zalesskyi.photosapp.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zalesskyi.domain.models.Photo
import com.zalesskyi.domain.usecases.AddFavoriteUseCase
import com.zalesskyi.domain.usecases.RemoveFavoriteUseCase
import com.zalesskyi.photosapp.base.BaseViewModel
import com.zalesskyi.photosapp.base.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface PhotoViewModel : BaseViewModel {

    val favoriteLiveData: LiveData<Photo>

    fun toggleFavorite(photo: Photo)
}

@HiltViewModel
class PhotoViewModelImpl
@Inject
constructor(private val addFavoriteUseCase: AddFavoriteUseCase,
            private val removeFavoriteUseCase: RemoveFavoriteUseCase) : BaseViewModelImpl(), PhotoViewModel {

    override val favoriteLiveData = MutableLiveData<Photo>()

    override fun toggleFavorite(photo: Photo) {
        viewModelScope.launch {
            if (photo.isFavorite) {
                removeFavoriteUseCase.execute(photo) {
                    onComplete = {
                        favoriteLiveData.postValue(photo.copy(isFavorite = !photo.isFavorite))
                    }
                }
            } else {
                addFavoriteUseCase.execute(photo) {
                    onComplete = {
                        favoriteLiveData.postValue(photo.copy(isFavorite = !photo.isFavorite))
                    }
                }
            }
        }
    }
}