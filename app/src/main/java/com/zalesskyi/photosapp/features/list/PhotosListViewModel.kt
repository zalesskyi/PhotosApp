package com.zalesskyi.photosapp.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zalesskyi.domain.models.DEFAULT_LIMIT
import com.zalesskyi.domain.models.Page
import com.zalesskyi.domain.models.Photo
import com.zalesskyi.domain.usecases.AddFavoriteUseCase
import com.zalesskyi.domain.usecases.GetPhotosUrlUseCase
import com.zalesskyi.domain.usecases.RemoveFavoriteUseCase
import com.zalesskyi.photosapp.base.BaseViewModel
import com.zalesskyi.photosapp.base.BaseViewModelImpl
import com.zalesskyi.photosapp.base.ProgressEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface PhotosListViewModel : BaseViewModel {

    val photosLiveData: LiveData<List<Photo>>

    val morePhotosLiveData: LiveData<List<Photo>>

    var needToLoadMore: Boolean

    fun loadPhotos()

    fun loadMorePhotos()

    fun toggleFavorite(photo: Photo)
}

@HiltViewModel
class PhotosListViewModelImpl
@Inject
constructor(private val getPhotosUrlUseCase: GetPhotosUrlUseCase,
            private val addFavoriteUseCase: AddFavoriteUseCase,
            private val removeFavoriteUseCase: RemoveFavoriteUseCase) : BaseViewModelImpl(), PhotosListViewModel {

    override val photosLiveData = MutableLiveData<List<Photo>>()

    override val morePhotosLiveData = MutableLiveData<List<Photo>>()

    override var needToLoadMore: Boolean = true

    private var offset: Int = 0

    override fun loadPhotos() {
        getPhotos(onComplete = {
            onArrived(photosLiveData, it)
        }, onStart = {
            progressLiveData.postValue(ProgressEvent.Show)
        }, onTerminate = {
            progressLiveData.postValue(ProgressEvent.Hide)
        })
    }

    override fun loadMorePhotos() {
        getPhotos(onComplete = {
            onArrived(morePhotosLiveData, it)
        }, onStart = {
            progressLiveData.postValue(ProgressEvent.LoadMore)
        }, onTerminate = {
            progressLiveData.postValue(ProgressEvent.Hide)
        })
    }

    override fun toggleFavorite(photo: Photo) {
        viewModelScope.launch {
            if (photo.isFavorite) {
                removeFavoriteUseCase.execute(photo) {
                    onComplete = {
                        photo.isFavorite = !photo.isFavorite
                    }
                }
            } else {
                addFavoriteUseCase.execute(photo) {
                    onComplete = {
                        photo.isFavorite = !photo.isFavorite
                    }
                }
            }
        }
    }

    private fun getPhotos(onComplete: (List<Photo>) -> Unit,
                          onStart: () -> Unit = {},
                          onTerminate: () -> Unit = {}) {
        viewModelScope.launch {
            getPhotosUrlUseCase.execute(Page(DEFAULT_LIMIT, offset)) {
                this.onComplete = {
                    onComplete(it)
                }
                this.onStart = {
                    onStart()
                }
                this.onTerminate = {
                    onTerminate()
                }
            }
        }
    }

    private fun onArrived(liveData: MutableLiveData<List<Photo>>, data: List<Photo>) {
        liveData.postValue(data)
        offset += data.size
        needToLoadMore = data.size >= DEFAULT_LIMIT
    }
}